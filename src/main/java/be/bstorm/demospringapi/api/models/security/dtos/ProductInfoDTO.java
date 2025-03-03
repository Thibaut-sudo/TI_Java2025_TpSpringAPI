package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.dl.entities.Product;

import java.math.BigDecimal;

public record ProductInfoDTO(
    Long id,
    String nom ,
    String description,
    String imageUrl,
    BigDecimal prix

    ){
    public static ProductInfoDTO fromProduct (Product product){
        return new ProductInfoDTO(product.getId(),product.getNom(),product.getDescription(),product.getImageUrl(),product.getPrix());
    }
}
