package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.dl.entities.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
    List<UserCharacter> findByUser(User user);
}