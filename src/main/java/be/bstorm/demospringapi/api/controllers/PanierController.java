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

/**
 * Contrôleur REST pour la gestion des paniers des utilisateurs.
 */
@RestController
@RequestMapping("/api/paniers")
@RequiredArgsConstructor
@Tag(name = "Panier", description = "API pour gérer les paniers des utilisateurs")
public class PanierController {

    private final PanierService panierService;

    /**
     * Récupère le panier associé à un utilisateur donné.
     *
     * @param userId L'ID de l'utilisateur dont on veut récupérer le panier.
     * @return Le panier de l'utilisateur, s'il existe.
     */
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

    /**
     * Ajoute un produit au panier spécifié.
     *
     * @param panierId      L'ID du panier où ajouter le produit.
     * @param produitPanier Le produit à ajouter dans le panier.
     * @return Le panier mis à jour avec le produit ajouté.
     */
    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Ajouter un produit au panier",
            description = "Ajoute un produit au panier spécifié par son ID."
    )
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

    /**
     * Met à jour le statut d'un panier existant.
     *
     * @param panierId      L'ID du panier à mettre à jour.
     * @param panierDetails Les nouvelles informations du panier (statut mis à jour).
     * @return Le panier mis à jour.
     */
    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Mettre à jour un panier",
            description = "Met à jour le statut du panier spécifié."
    )
    @PutMapping("/{panierId}")
    public ResponseEntity<Panier> updatePanier(
            @Parameter(description = "ID du panier à mettre à jour", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID panierId,
            @RequestBody Panier panierDetails
    ) {
        Panier updatedPanier = panierService.updateStatut(panierId, panierDetails.getStatut());
        return ResponseEntity.ok(updatedPanier);
    }

    /**
     * Supprime un panier en fonction de son ID.
     *
     * @param panierId L'ID du panier à supprimer.
     * @return Une réponse sans contenu si la suppression est réussie.
     */
    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Supprimer un panier",
            description = "Supprime le panier correspondant à l'ID fourni."
    )
    @DeleteMapping("/{panierId}")
    public ResponseEntity<Void> deletePanier(
            @Parameter(description = "ID du panier à supprimer", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID panierId
    ) {
        panierService.supprimerPanier(panierId);
        return ResponseEntity.noContent().build();
    }
}
