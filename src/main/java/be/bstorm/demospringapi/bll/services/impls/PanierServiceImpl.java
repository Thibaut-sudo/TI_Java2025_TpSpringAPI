/*package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.bll.services.PanierService;
import be.bstorm.demospringapi.dal.repositories.PanierRepository;
import be.bstorm.demospringapi.dl.entities.Panier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;

    @Override
    public Panier ajouterPanier(Panier panier) {
        Optional<Panier> existingPanier = panierRepository.findByUser(panier.getId_Panier());
        if (existingPanier.isPresent()) {
            throw new RuntimeException("a deja un panier");
        }
        return panierRepository.save(panier);
    }

    @Override
    public Optional<Panier> getPanierByUser(UUID idUser) {
        return Optional.empty();
    }

    @Override
    public Panier updateStatut(UUID Id_Panier, String statut) {
        Optional<Panier> panierOpt = panierRepository.findById(Id_Panier);
        if (panierOpt.isPresent()) {
            Panier panier = panierOpt.get();
            panier.setStatut(statut);
            return panierRepository.save(panier);
        }
        throw new RuntimeException("Panier vide");
    }

    @Override
    public Panier updatePrixTotal(UUID Id_Panier, int prixTotal) {
        return null;
    }

    @Override
    public void supprimerPanier(UUID Id_Panier) {
        panierRepository.deleteById(Id_Panier);
    }
}*/
