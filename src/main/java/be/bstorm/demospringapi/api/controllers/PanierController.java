package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.bll.services.PanierService;
import be.bstorm.demospringapi.bll.services.UserService;
import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.ProduitPanier;
import be.bstorm.demospringapi.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/paniers")
@RequiredArgsConstructor
public class PanierController {

    private final PanierService panierService;

    @GetMapping("/{userId}")
    public ResponseEntity<Panier> getPanierByUser(@PathVariable UUID userId) {
        Optional<Panier> panier = panierService.getPanierByUser(userId);
        return ResponseEntity.ok(panier.get());
    }

    @PostMapping("/{panierId}/ajouter-produit")
    public ResponseEntity<Panier> addProduitToPanier(
            @PathVariable UUID panierId,
            @RequestBody ProduitPanier produitPanier) {
        Panier panierUpdated = panierService.addProduitToPanier(panierId, produitPanier);
        panierUpdated.setStatut("attente");

        return ResponseEntity.ok(panierUpdated);
    }

    @PutMapping("/{panierId}")
    public ResponseEntity<Panier> updatePanier(
            @PathVariable UUID panierId,
            @RequestBody Panier panierDetails) {
        Panier updatedPanier = panierService.updateStatut(panierId, panierDetails.getStatut());
        return ResponseEntity.ok(updatedPanier);
    }

    @DeleteMapping("/{panierId}")
    public ResponseEntity<Void> deletePanier(@PathVariable UUID panierId) {
        panierService.supprimerPanier(panierId);
        return ResponseEntity.noContent().build();
    }
}

