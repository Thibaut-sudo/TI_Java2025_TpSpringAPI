package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.bll.services.PanierService;
import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.dal.repositories.PanierRepository;
import be.bstorm.demospringapi.dal.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final UserRepository userRepository;

    public PanierServiceImpl(PanierRepository panierRepository, UserRepository userRepository) {
        this.panierRepository = panierRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Panier ajouterPanier(Panier panier) {
        Optional<Panier> existingPanier = panierRepository.findByUser(panier.getUser());
        if (existingPanier.isPresent()) {
            throw new IllegalStateException("l utilisateur possède déjà un panier.");
        }

        return panierRepository.save(panier);
    }

    @Override
    public Optional<Panier> getPanierByUser(UUID idUser) {
        return Optional.empty();
    }


    @Override
    @Transactional
    public Panier updateStatut(UUID idPanier, String statut) {
        Panier panier = panierRepository.findById(idPanier)
                .orElseThrow(() -> new EntityNotFoundException("panier non trouvé"));
        panier.setStatut(statut);
        return panierRepository.save(panier);
    }

    @Override
    @Transactional
    public Panier updatePrixTotal(UUID idPanier, int prixTotal) {
        Panier panier = panierRepository.findById(idPanier)
                .orElseThrow(() -> new EntityNotFoundException("panier non trouvé"));
        panier.setPrixTotal(prixTotal);
        return panierRepository.save(panier);
    }

    @Override
    public void supprimerPanier(UUID idPanier) {
        if (!panierRepository.existsById(idPanier)) {
            throw new EntityNotFoundException("panier non trouvé");
        }
        panierRepository.deleteById(idPanier);
    }
}