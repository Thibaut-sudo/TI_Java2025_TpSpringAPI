package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Personnage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Personnage, Long> {
    List<Personnage> findByRarity(String rarity);
}