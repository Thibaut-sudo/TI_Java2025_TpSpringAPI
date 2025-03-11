package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.bll.exceptions.Panier.PanierNotFoundException;
import be.bstorm.demospringapi.bll.exceptions.product.ProductNotFoundException;
import be.bstorm.demospringapi.bll.services.ProductService;
import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import be.bstorm.demospringapi.il.utils.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> getProduct(List<SearchParam<Product>> searchParams, Pageable pageable) {
        Page<Product> products;

        if (searchParams.isEmpty()) {
            products = productRepository.findAll(pageable);
        } else {
            products = productRepository.findAll(
                    Specification.allOf(
                            searchParams.stream()
                                    .map(SearchSpecification::search)
                                    .toList()
                    ),
                    pageable
            );
        }
        if (products.isEmpty()) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Produit non trouvé");
        }
        return products;
    }

    @Override
    public List<ProductDTO> foundAll() {
        List<ProductDTO> products = productRepository.findAll().stream()
                .map(ProductDTO::fromProduct)
                .toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Produit non trouvé");
        }
        return products;
    }

    @Override
    public ProductDTO foundOneDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, "details du Produit non trouvé"));

        return ProductDTO.fromProduct(product);
    }

    @Override
    public void insert(ProductForm productForm) {
        Product product = productForm.toProduct();
        productRepository.save(product);
    }

    @Override
    public ProductDTO update(Long id, ProductForm productForm) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, "impossible de modifier Produit"));

        product.setNom(productForm.nom());
        product.setDescription(productForm.description()); // Correction ici : utiliser `description()`
        product.setImageUrl(productForm.imageUrl());
        product.setPrix(productForm.prix());

        productRepository.save(product);

        return ProductDTO.fromProduct(product);
    }

    @Override
    public void delete(long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "impossible de supprimer le Produit");
        }
        productRepository.deleteById(id);
    }
}