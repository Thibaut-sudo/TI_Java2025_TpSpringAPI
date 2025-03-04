package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.bll.services.ProductService;
import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import be.bstorm.demospringapi.il.utils.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository ;

    @Override
    public Page<Product> getProduct(List<SearchParam<Product>> searchParams, Pageable pageable) {

        if (searchParams.isEmpty()){
            return productRepository.findAll(pageable);
        }
        return productRepository.findAll(
                Specification.allOf(
                        searchParams.stream()
                                .map(SearchSpecification::search)
                                .toList()
                ),
                pageable
        );
    }

    @Override
    public List<ProductDTO> foundAll() {

        //voir en bas autre version
        return productRepository.findAll().stream()
                .map(ProductDTO::fromProduct)
                .toList();
    }

    @Override
    public ProductDTO foundOneDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

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
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

        product.setNom(productForm.nom());
        product.setDescription(productForm.nom());
        product.setImageUrl(productForm.imageUrl());
        product.setPrix(productForm.prix());

        productRepository.save(product);

        return ProductDTO.fromProduct(product);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }


}