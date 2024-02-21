package fr.charly.chatApp.service;

import fr.charly.chatApp.entity.Comment;
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



//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
    public Comment sendMessage(
            @Payload Comment comment
    ) {
        return comment;
    }

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
    public Comment addUser(
            @Payload Comment comment,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", comment.getChatter());
        return comment;
    }



}
