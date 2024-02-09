package fr.charly.chatApp.repository;

import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.User;
import fr.charly.chatApp.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickname);

}
