package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PanierRepository extends JpaRepository<Panier, UUID> {

    List<Panier> findByUser_IdUser(UUID idUser);
    List<Panier> findByStatut(String statut);
}