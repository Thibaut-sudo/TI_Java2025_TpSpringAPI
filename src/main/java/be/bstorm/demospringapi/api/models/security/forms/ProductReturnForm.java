package be.bstorm.demospringapi.api.models.security.forms;

import be.bstorm.demospringapi.api.models.security.dtos.ProductReturnDTO;
import be.bstorm.demospringapi.dl.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductReturnForm (
            @NotBlank @Size(max = 30)
            String nom,

            @NotBlank @Size(max = 250)
            String description,

            @NotNull
            BigDecimal prix
    ) {


    public Product toProductreturn() {
        return new Product(this.nom,this.description,this.prix);
    }
}


