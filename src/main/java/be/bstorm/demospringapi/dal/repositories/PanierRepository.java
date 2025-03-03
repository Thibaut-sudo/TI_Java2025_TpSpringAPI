package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Integer> {

    Optional<Panier> findByUser_IdUser(UUID idUser);
    Optional<Panier> findByStatut(String statut);

}
