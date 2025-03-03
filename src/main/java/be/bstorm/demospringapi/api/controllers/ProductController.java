package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.ProductInfoDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.bll.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductInfoDTO>> getAll() {
        List<ProductInfoDTO> po = productService.foundAll();
        return ResponseEntity.ok(po);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInfoDTO> getOneDetails(@PathVariable Long id) {
        ProductInfoDTO pi = productService.foundOneDetails(id);
        return ResponseEntity.ok(pi);
    }

    @PostMapping
    public ResponseEntity<Void> postCreate(
            @Valid @RequestBody ProductForm form
    ) {
        productService.insert(form);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> postUpdate(
            @PathVariable Long id,
            @Valid @RequestBody ProductForm form
    ) {
        productService.update(id,form);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> postDelete(
            @PathVariable Long id
    ) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}