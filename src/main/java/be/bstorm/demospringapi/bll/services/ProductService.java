package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.api.models.security.forms.ProductReturnForm;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Page<Product> getProduct(List<SearchParam<Product>> searchParams, Pageable pageable);
    List<ProductDTO> foundAll();
    ProductDTO foundOneDetails(Long id);
    void insert(ProductForm productForm);
    ProductDTO update(Long id, ProductForm productForm);
    void delete(long id);
    List<Product> researchProducts(String query, String site) throws IOException;

}
