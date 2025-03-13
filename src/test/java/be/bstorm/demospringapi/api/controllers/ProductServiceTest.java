package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.bll.services.impls.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void testFoundAll() {
        Product product = new Product();
        product.setId(1L);
        product.setNom("Test Product");

        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductDTO> result = productService.foundAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).nom());
    }

    @Test
    void testFoundOneDetails() {
        Product product = new Product();
        product.setId(1L);
        product.setNom("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO result = productService.foundOneDetails(1L);
        assertNotNull(result);
        assertEquals("Test Product", result.nom());
    }

    @Test
    void testInsert() {
        ProductForm productForm = new ProductForm("New Product", null, null, null);


        Product product = new Product();
        product.setNom(productForm.nom());

        when(productRepository.save(any(Product.class))).thenReturn(product);

        assertDoesNotThrow(() -> productService.insert(productForm));
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdate() {
        ProductForm productForm = new ProductForm("Updated Product", null, null, null);


        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setNom("Old Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        ProductDTO result = productService.update(1L, productForm);
        assertNotNull(result);
        assertEquals("Updated Product", result.nom());
    }

    @Test
    void testDelete() {
        // Given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(productId);

        // When & Then
        assertDoesNotThrow(() -> productService.delete(productId));

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }



}
