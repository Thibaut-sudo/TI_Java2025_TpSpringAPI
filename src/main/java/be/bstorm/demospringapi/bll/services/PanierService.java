package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.ProduitPanier;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface PanierService {

    Panier ajouterPanier(Panier panier);
    Optional<Panier> getPanierByUser(UUID idUser);
    Panier updateStatut(UUID idPanier, String statut);
    Panier updatePrixTotal(UUID idPanier, BigDecimal prixTotal);
    void supprimerPanier(UUID idPanier);
    Panier addProduitToPanier(UUID idpanier, ProduitPanier produitPanier);
}
