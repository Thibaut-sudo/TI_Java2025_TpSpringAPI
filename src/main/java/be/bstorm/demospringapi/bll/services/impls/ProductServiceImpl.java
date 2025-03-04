package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.bll.services.ProductService;
import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dl.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository ;

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