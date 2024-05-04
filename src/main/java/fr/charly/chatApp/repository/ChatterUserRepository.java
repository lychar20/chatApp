package fr.charly.chatApp.repository;

import fr.charly.chatApp.entity.Chatter;
import fr.charly.chatApp.entity.User;
import fr.charly.chatApp.entity.enumo.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatterUserRepository extends JpaRepository<Chatter, Long> {

    List<Chatter> findAllByStatus(Status status);


}
