package fr.charly.chatApp.repository;

import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.entity.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    Optional<Comment> findById(Long id);

    Page<Comment> findAllByThreadAndModeratorIsNullAndCommentFromIsNull(Thread thread, Pageable pageable);

    Page<Comment> findByThreadAndModeratorIsNotNull(Thread thread,Pageable pageable);
    Page<Comment> findAllByCommentFromAndModeratorIsNull(Comment commentParent , Pageable pageable);
    Page<Comment> findAllByCommentFromAndModeratorIsNotNull(Comment commentParent , Pageable pageable);
    Page<Comment> findByThreadAndModeratorIsNull(Thread thread,Pageable pageable);
    Page<Comment> findAllByThreadAndModeratorIsNotNullAndCommentFromIsNull(Thread thread,Pageable pageable);

    Page<Comment> findAllByChatterNickname(String nickname, Pageable pageable);
}
