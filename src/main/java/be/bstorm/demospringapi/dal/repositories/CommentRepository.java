package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
