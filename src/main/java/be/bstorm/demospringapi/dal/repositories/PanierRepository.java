package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Integer> {

    Optional<Panier> findByUser(User user);
    Optional<Panier> findByStatut(String statut);
}
