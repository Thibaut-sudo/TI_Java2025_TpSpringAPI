package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.StockDTO;
import be.bstorm.demospringapi.api.models.security.forms.StockForm;
import be.bstorm.demospringapi.bll.services.StockService;
import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
@Tag(name = "Stock", description = "API pour gérer les stocks") // Regroupe les endpoints dans Swagger
public class StockController {

    private final StockService stockService;

    @Operation(
            summary = "Récupérer tous les stocks",
            description = "Retourne la liste complète des stocks enregistrés, avec support de pagination et tri."
    )
    @GetMapping
    public ResponseEntity<List<StockDTO>> getStocks(
            @RequestParam Map<String, String> params,
            @Parameter(description = "Numéro de la page", example = "0")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "Nombre d'éléments par page", example = "10")
            @RequestParam(required = false, defaultValue = "10") int size,
            @Parameter(description = "Champ utilisé pour trier les résultats", example = "id")
            @RequestParam(required = false, defaultValue = "id") String sort
    ) {
        List<SearchParam<Stock>> searchParams = SearchParam.create(params);
        List<StockDTO> stock = stockService.getStocks(
                        searchParams,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))
                ).stream()
                .map(StockDTO::fromStock)
                .toList();

        return ResponseEntity.ok(stock);
    }

    @Operation(
            summary = "Créer un nouveau stock",
            description = "Ajoute un nouvel élément au stock."
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<StockDTO> createStock(
            @Valid @RequestBody StockForm form
    ) {
        stockService.insert(form);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Mettre à jour un stock",
            description = "Modifie les informations d'un stock existant en fonction de son ID."
    )
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(
            @Parameter(description = "ID du stock à mettre à jour", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody StockForm form
    ) {
        stockService.update(id, form);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Supprimer un stock",
            description = "Supprime un stock de la base de données en fonction de son ID."
    )
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<StockDTO> deleteStock(
            @Parameter(description = "ID du stock à supprimer", example = "1")
            @PathVariable Long id
    ) {
        stockService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Récupérer la quantité de stock d'un produit",
            description = "Retourne la quantité en stock d'un produit spécifique en fonction de son ID."
    )
    @GetMapping("/product/{id}")
    public ResponseEntity<Integer> getStockByProduct(
            @Parameter(description = "ID du produit", example = "1001")
            @PathVariable Long id
    ) {
        Integer quantitee = stockService.getStockByProduct(id);
        return ResponseEntity.ok(quantitee);
    }
    @Operation(
            summary = " Récupérer les stocks d'un utilisateur",
            description = "Retourne la liste des stocks d'un utilisateur spécifique en fonction de son ID."
            )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/stock/user")
    public ResponseEntity<List<Stock>> getStockByUser(

            @AuthenticationPrincipal User user
    ) {
        List<Stock> stock = stockService.getStockByUser(user.getId());

        return ResponseEntity.ok(stock);
    }
}
