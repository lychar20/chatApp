package fr.charly.chatApp.service;

import fr.charly.chatApp.DTO.CommentDTO;
import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.*;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.repository.CommentRepository;
import fr.charly.chatApp.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService implements DAOFindByIdInterface<Comment> {

    private CommentRepository commentRepository;
    private UserService userService;
    private ThreadService threadService;

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                ;
    }


    public Page<Comment> findAllByThread (Thread thread, Pageable pageable) {
        return commentRepository.findAllByThreadAndModeratorIsNullAndCommentFromIsNull( thread , pageable);
    }

    public Comment createComment(CommentDTO commentDTO, Thread thread, String name, Long id) {

        Optional<Comment> optionalComment = commentRepository.findById(id);

        Comment comment = new Comment();
        comment.setThread(thread);
        comment.setChatter((Chatter) userService.findByNickname(name));

        if (optionalComment.isPresent()) {
            comment.setCommentFrom(optionalComment.get());
        }

        comment.setContent(commentDTO.getDescription());
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.saveAndFlush(comment);
    }


    public void moderateComment(String nickname, Long id) {
        Comment comment = findById(id);
        comment.setModerator((Moderator) userService.findByNickname(nickname));
        comment.setModeratedAt(LocalDateTime.now());
        commentRepository.flush();
    }


    public Page<Comment> getPageCommentOrdered(Principal principal, Thread thread, Pageable pageable) {
        User user = userService.findByNickname(principal.getName());
        Page<Comment> pageComments = commentRepository.findAllByThreadAndModeratorIsNullAndCommentFromIsNull( thread , pageable);
        if (user.isModerator()) {
            Sort.Order order = pageable.getSort().getOrderFor("moderator");
            if (order != null) {
                if (order.isAscending()) {
                    pageComments = commentRepository.findAllByThreadAndModeratorIsNullAndCommentFromIsNull(thread, pageable);
                } else {
                    pageComments = commentRepository.findByThreadAndModeratorIsNotNull(thread, pageable);
                }
            } else {
                pageComments = commentRepository.findAll(pageable);
            }
        }
        return pageComments;
    }


    }
