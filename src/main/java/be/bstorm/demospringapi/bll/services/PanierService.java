package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.dl.entities.Panier;

import java.util.Optional;
import java.util.UUID;

public interface PanierService {

    Panier ajouterPanier(Panier panier);
    Optional<Panier> getPanierByUser(UUID idUser);
    Panier updateStatut(UUID Id_Panier, String statut);
    Panier updatePrixTotal(UUID Id_Panier, int prixTotal);
    void supprimerPanier(UUID Id_Panier);
}
