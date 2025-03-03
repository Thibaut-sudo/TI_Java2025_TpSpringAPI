package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.dtos.ProductInfoDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.bll.services.ProductService;
import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.dl.entities.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository ;

    @Override
    public List<ProductInfoDTO> foundAll() {

        //voir en bas autre version
        return productRepository.findAll().stream()
                .map(ProductInfoDTO::fromProduct)
                .toList();
    }

    @Override
    public ProductInfoDTO foundOneDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

        return ProductInfoDTO.fromProduct(product);
    }

    @Override
    public void insert(ProductForm productForm) {
        Product product = productForm.toProduct();
        productRepository.save(product);
    }
    @Override
    public ProductInfoDTO update(Long id, ProductForm productForm) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

        product.setNom(productForm.nom());
        product.setDescription(productForm.nom());
        product.setImageUrl(productForm.imageUrl());
        product.setPrix(productForm.prix());

        productRepository.save(product);

        return ProductInfoDTO.fromProduct(product);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }


}