package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.dl.entities.Product;

import java.math.BigDecimal;

public record ProductDTO(
    Long id,
    String nom ,
    String description,
    String imageUrl,
    BigDecimal prix

    ){
    public static ProductDTO fromProduct (Product product){
        return new ProductDTO(product.getId(),product.getNom(),product.getDescription(),product.getImageUrl(),product.getPrix());
    }
}
