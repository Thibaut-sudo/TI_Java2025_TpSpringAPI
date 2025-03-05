package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.bll.services.PanierService;
import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.dl.entities.ProduitPanier;
import be.bstorm.demospringapi.dal.repositories.PanierRepository;
import be.bstorm.demospringapi.dal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Panier ajouterPanier(Panier panier) {
        panier.setStatut("attente");
        panier.setPrixTotal(BigDecimal.valueOf(0));
        return panierRepository.save(panier);
    }

    @Override
    public Optional<Panier> getPanierByUser(UUID idUser) {
        return panierRepository.findById(idUser);
    }

    @Override
    public Panier updateStatut(UUID idPanier, String statut) {
        Panier panier = panierRepository.findById(idPanier)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        panier.setStatut(statut);
        return panierRepository.save(panier);
    }

    @Override
    public Panier updatePrixTotal(UUID idPanier, BigDecimal prixTotal) {
        Panier panier = panierRepository.findById(idPanier)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        panier.setPrixTotal(prixTotal);
        return panierRepository.save(panier);
    }

    @Override
    public void supprimerPanier(UUID idPanier) {
        Panier panier = panierRepository.findById(idPanier)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        panierRepository.delete(panier);
    }

    @Override
    public Panier addProduitToPanier(UUID idpanier, ProduitPanier produitPanier) {
        Panier panier = panierRepository.findById(idpanier)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Product produit = productRepository.findById(produitPanier.getId())
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        panier.getProduits().add(produitPanier);
        panier.getPrixTotal().add(produit.getPrix());
        panier.getStatut();
        return panierRepository.save(panier);
    }
}