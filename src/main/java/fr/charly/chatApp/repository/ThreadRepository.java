package fr.charly.chatApp.repository;

import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.entity.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
}
