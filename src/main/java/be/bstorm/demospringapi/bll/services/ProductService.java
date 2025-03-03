package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.api.models.security.dtos.ProductInfoDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;

import java.util.List;

public interface ProductService {
    List<ProductInfoDTO> foundAll();
    ProductInfoDTO foundOneDetails(Long id);
    void insert(ProductForm productForm);
    ProductInfoDTO update(Long id, ProductForm productForm);
    void delete(long id);
}
