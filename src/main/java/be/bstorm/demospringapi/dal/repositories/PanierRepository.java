package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PanierRepository extends JpaRepository<Panier, UUID> {

    Optional<Panier> findByUser(User user);
    Optional<Panier> findByStatut(String statut);
}
