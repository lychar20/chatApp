package fr.charly.chatApp.repository;

import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.entity.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByThreadAndModeratorIsNull(Thread thread, Pageable pageable);

    Page<Comment> findAllByChatterNickname(String nickname, Pageable pageable);
}
