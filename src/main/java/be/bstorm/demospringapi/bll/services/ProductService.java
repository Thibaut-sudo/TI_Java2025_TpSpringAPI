package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;

import java.util.List;

public interface ProductService {
    List<ProductDTO> foundAll();
    ProductDTO foundOneDetails(Long id);
    void insert(ProductForm productForm);
    ProductDTO update(Long id, ProductForm productForm);
    void delete(long id);
}
