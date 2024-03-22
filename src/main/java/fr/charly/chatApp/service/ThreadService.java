package fr.charly.chatApp.service;

import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.repository.CommentRepository;
import fr.charly.chatApp.repository.ThreadRepository;
import fr.charly.chatApp.service.interfaces.DAOFindAllInterface;
import fr.charly.chatApp.service.interfaces.DAOFindByIdInterface;
import fr.charly.chatApp.service.interfaces.DAOFindBySlugInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ThreadService implements DAOFindByIdInterface<Thread>, DAOFindBySlugInterface<Thread>, DAOFindAllInterface<Thread> {

    private ThreadRepository threadRepository;
    @Override
    public Thread findById(Long id) {
        return threadRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                ;
    }

    @Override
    public Thread findBySlug(String slug) {
        return threadRepository.findBySlug(slug)
                .orElseThrow(EntityNotFoundException::new)
                ;
    }


    public Thread createThread(ThreadDTO threadDTO, Category category) {
        Thread thread = new Thread();
        thread.setCategory(category);
        thread.setTitle(threadDTO.getTitle());
        return threadRepository.saveAndFlush(thread);
    }


    @Override
    public List<Thread> findAll() {
        return threadRepository.findAll();
    }

    @Override
    public List<Thread> findAllSorted() {
        return null;
    }
}
