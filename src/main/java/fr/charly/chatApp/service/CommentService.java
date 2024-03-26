package fr.charly.chatApp.service;

import fr.charly.chatApp.DTO.CommentDTO;
import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.repository.CommentRepository;
import fr.charly.chatApp.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService implements DAOFindByIdInterface<Comment> {

    private CommentRepository commentRepository;
    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                ;
    }



    public Comment createComment(CommentDTO commentDTO, Thread thread) {
        Comment comment = new Comment();
        comment.setThread(thread);
        comment.setContent(commentDTO.getDescription());
        return commentRepository.saveAndFlush(comment);
    }


}
