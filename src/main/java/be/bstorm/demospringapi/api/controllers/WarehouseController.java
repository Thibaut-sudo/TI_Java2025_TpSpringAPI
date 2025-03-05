package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.WarehouseDTO;
import be.bstorm.demospringapi.api.models.security.forms.WarehouseForm;
import be.bstorm.demospringapi.bll.services.WarehouseService;
import be.bstorm.demospringapi.dl.entities.Warehouse;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse")
@Tag(name = "Warehouse", description = "Gestion des entrepôts") // Regroupe les endpoints sous "Warehouse"
public class WarehouseController {

    private final WarehouseService warehouseService;

    /**
     * Récupère la liste des entrepôts avec pagination et tri.
     *
     * @param params Paramètres de recherche dynamiques.
     * @param page Numéro de la page (par défaut 0).
     * @param size Nombre d'éléments par page (par défaut 10).
     * @param sort Champ utilisé pour trier (par défaut "id").
     * @return Liste paginée des entrepôts sous forme de DTO.
     */
    @Operation(summary = "Obtenir la liste des entrepôts", description = "Permet de récupérer tous les entrepôts avec filtres, pagination et tri.")
    @ApiResponse(responseCode = "200", description = "Liste des entrepôts récupérée avec succès",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WarehouseDTO.class)))
    @GetMapping
    public ResponseEntity<List<WarehouseDTO>> getWarehouse(
            @RequestParam(required = false) Map<String, String> params,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort
    ) {
        List<SearchParam<Warehouse>> searchParams = SearchParam.create(params);

        List<WarehouseDTO> warehouseList = warehouseService.getWarehouse(
                        searchParams,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))
                ).stream()
                .map(WarehouseDTO::fromWarehouse)
                .toList();

        return ResponseEntity.ok(warehouseList);
    }

    /**
     * Crée un nouvel entrepôt.
     *
     * @param form Objet contenant les informations du nouvel entrepôt.
     * @return Code 204 (No Content) en cas de succès.
     */
    @Operation(summary = "Créer un entrepôt", description = "Ajoute un nouvel entrepôt à la base de données.")
    @ApiResponse(responseCode = "204", description = "Entrepôt créé avec succès")
    @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content)
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(
            @Valid @RequestBody WarehouseForm form
    ) {
        warehouseService.insert(form);
        return ResponseEntity.noContent().build();
    }

    /**
     * Supprime un entrepôt par son ID.
     *
     * @param id Identifiant de l'entrepôt à supprimer.
     * @return Code 204 (No Content) en cas de succès.
     */
    @Operation(summary = "Supprimer un entrepôt", description = "Supprime un entrepôt à partir de son ID.")
    @ApiResponse(responseCode = "204", description = "Entrepôt supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Entrepôt non trouvé", content = @Content)
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<WarehouseDTO> deleteWarehouse(
            @PathVariable Long id
    ) {
        warehouseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
