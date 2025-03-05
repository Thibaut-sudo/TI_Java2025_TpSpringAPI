package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.bll.services.PanierService;
import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.ProduitPanier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/paniers")
@RequiredArgsConstructor
@Tag(name = "Panier", description = "API pour gérer les paniers des utilisateurs")
public class PanierController {

    private final PanierService panierService;

    @Operation(
            summary = "Récupérer le panier d'un utilisateur",
            description = "Renvoie le panier associé à un utilisateur via son ID."
    )
    @GetMapping("/{userId}")
    public ResponseEntity<Panier> getPanierByUser(
            @Parameter(description = "ID de l'utilisateur", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID userId
    ) {
        Optional<Panier> panier = panierService.getPanierByUser(userId);
        return ResponseEntity.ok(panier.get());

    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{panierId}/ajouter-produit")
    public ResponseEntity<Panier> addProduitToPanier(
            @Parameter(description = "ID du panier", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID panierId,
            @RequestBody ProduitPanier produitPanier
    ) {
        Panier panierUpdated = panierService.addProduitToPanier(panierId, produitPanier);
        panierUpdated.setStatut("attente");

        return ResponseEntity.ok(panierUpdated);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{panierId}")
    public ResponseEntity<Panier> updatePanier(
            @Parameter(description = "ID du panier à mettre à jour", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID panierId,
            @RequestBody Panier panierDetails
    ) {
        Panier updatedPanier = panierService.updateStatut(panierId, panierDetails.getStatut());
        return ResponseEntity.ok(updatedPanier);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{panierId}")
    public ResponseEntity<Void> deletePanier(
            @Parameter(description = "ID du panier à supprimer", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID panierId
    ) {
        panierService.supprimerPanier(panierId);
        return ResponseEntity.noContent().build();
    }
}
