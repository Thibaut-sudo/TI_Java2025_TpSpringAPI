package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.dtos.ProductReturnDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.api.models.security.forms.ProductReturnForm;
import be.bstorm.demospringapi.bll.services.ProductService;
import be.bstorm.demospringapi.dl.entities.Product;
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
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "Produit", description = "API pour gérer les produits") // Regroupe les endpoints dans Swagger
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Récupérer tous les produits",
            description = "Retourne la liste complète des produits enregistrés avec la possibilité de filtrer et paginer."
    )
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(
            @RequestParam(required = false) Map<String, String> params,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort
    ) {
        List<SearchParam<Product>> searchParams = SearchParam.create(params);
        List<ProductDTO> products = productService.getProduct(
                searchParams,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))
        ).stream().map(ProductDTO::fromProduct).toList();

        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "Récupérer un produit par son ID",
            description = "Retourne les détails d'un produit spécifique."
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(
            @Parameter(description = "ID du produit à récupérer", example = "1")
            @PathVariable Long id
    ) {
        ProductDTO product = productService.foundOneDetails(id);
        return ResponseEntity.ok(product);
    }

    @Operation(
            summary = "Créer un nouveau produit",
            description = "Ajoute un nouveau produit à la base de données."
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Void> createProduct(
            @Valid @RequestBody ProductForm form
    ) {
        productService.insert(form);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Mettre à jour un produit",
            description = "Modifie les informations d'un produit existant en fonction de son ID."
    )
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @Parameter(description = "ID du produit à modifier", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProductForm form
    ) {
        productService.update(id, form);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Supprimer un produit",
            description = "Supprime un produit en fonction de son ID."
    )
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID du produit à supprimer", example = "1")
            @PathVariable Long id
    ) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }




    // Point d'entrée API pour rechercher des produits similaires
    @Operation(
            summary = "Rechercher un produit",
            description = "Rechercher un produit par mot-clé et site."
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @Parameter(description = "Produit à rechercher", example = "Soumission")
            @RequestParam String query,
            @Parameter(description = "Site ou chercher", example = "https://books.toscrape.com")
            @RequestParam String site) throws IOException {

        List<Product> productsReturn = productService.researchProducts(query, site);

        return ResponseEntity.ok(productsReturn);
    }
}


