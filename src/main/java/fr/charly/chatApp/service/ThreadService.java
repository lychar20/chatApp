package fr.charly.chatApp.service;

import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.repository.CommentRepository;
import fr.charly.chatApp.repository.ThreadRepository;
import fr.charly.chatApp.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ThreadService implements DAOFindByIdInterface<Thread> {

    private ThreadRepository threadRepository;
    @Override
    public Thread findById(Long id) {
        return threadRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                ;
    }


    public Thread createThread(ThreadDTO threadDTO) {
        Thread thread = new Thread();
        thread.setTitle(threadDTO.getTitle());
        return threadRepository.saveAndFlush(thread);
    }




}
