package fr.charly.chatApp.service;

import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.Chatter;
import fr.charly.chatApp.entity.enumo.Status;
import fr.charly.chatApp.repository.CategoryRepository;
import fr.charly.chatApp.repository.ChatterUserRepository;
import fr.charly.chatApp.service.interfaces.DAOFindAllInterface;
import fr.charly.chatApp.service.interfaces.DAOFindBySlugInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatterUserService  {


    private ChatterUserRepository chatterUserRepository;

    public void saveUser(Chatter chatter) {
        chatter.setStatus(Status.ONLINE);
        chatterUserRepository.save(chatter);
    }

    public void disconnect(Chatter chatter) {
        var storedUser = chatterUserRepository.findById(chatter.getId()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            chatterUserRepository.save(storedUser);
        }
    }

    public List<Chatter> findConnectedUsers() {
        return chatterUserRepository.findAllByStatus(Status.ONLINE);
    }


}
