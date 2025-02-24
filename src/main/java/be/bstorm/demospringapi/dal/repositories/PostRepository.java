package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
