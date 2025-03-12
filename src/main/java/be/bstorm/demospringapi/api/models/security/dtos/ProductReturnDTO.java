package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.api.models.security.forms.ProductReturnForm;
import be.bstorm.demospringapi.dl.entities.Product;

import java.math.BigDecimal;

public record ProductReturnDTO (

    String nom ,
    String description,

    BigDecimal prix

    ){
    public static ProductReturnDTO fromProductReturn (Product product){
        return new ProductReturnDTO(product.getNom(), product.getDescription(), product.getPrix());

    }
}