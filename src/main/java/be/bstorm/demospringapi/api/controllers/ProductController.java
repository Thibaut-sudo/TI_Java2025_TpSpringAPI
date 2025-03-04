package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.dtos.StockDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.bll.services.ProductService;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll (
            @RequestParam Map<String,String> params,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort
    ) {
        List<SearchParam<Product>> searchParams = SearchParam.create(params);
        List<ProductDTO> po = productService.getProduct(
                searchParams,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))
                ).stream()
                .map(ProductDTO::fromProduct)
                .toList();

        return ResponseEntity.ok(po);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOneDetails(@PathVariable Long id) {
        ProductDTO pi = productService.foundOneDetails(id);
        return ResponseEntity.ok(pi);
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Void> postCreate(
            @Valid @RequestBody ProductForm form
    ) {
        productService.insert(form);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Void> postUpdate(
            @PathVariable Long id,
            @Valid @RequestBody ProductForm form
    ) {
        productService.update(id,form);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> postDelete(
            @PathVariable Long id
    ) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}