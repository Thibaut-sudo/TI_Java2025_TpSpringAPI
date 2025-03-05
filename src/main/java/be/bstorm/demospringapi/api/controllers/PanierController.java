package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.bll.services.PanierService;
import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.ProduitPanier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{panierId}/ajouter-produit")
    public ResponseEntity<Panier> addProduitToPanier(
            @PathVariable UUID panierId,
            @RequestBody ProduitPanier produitPanier) {
        Panier panierUpdated = panierService.addProduitToPanier(panierId, produitPanier);
        panierUpdated.setStatut("attente");
        return ResponseEntity.ok(panierUpdated);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{panierId}")
    public ResponseEntity<Panier> updatePanier(
            @PathVariable UUID panierId,
            @RequestBody Panier panierDetails) {
        Panier updatedPanier = panierService.updateStatut(panierId, panierDetails.getStatut());
        return ResponseEntity.ok(updatedPanier);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{panierId}")
    public ResponseEntity<Void> deletePanier(@PathVariable UUID panierId) {
        panierService.supprimerPanier(panierId);
        return ResponseEntity.noContent().build();
    }
}

