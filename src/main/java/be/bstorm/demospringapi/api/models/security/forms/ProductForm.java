package be.bstorm.demospringapi.api.models.security.forms;

import be.bstorm.demospringapi.dl.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

public record ProductForm (
        @NotBlank @Size(max = 30)
        String nom,

        @NotBlank @Size(max = 250)
        String description,

        @NotBlank
        String imageUrl,

        @NotBlank @Size(max = 30)
        BigDecimal prix
){
    public ProductForm {
    }

    public Product toProduct() {
        return new Product(this.nom,this.description,this.imageUrl,this.prix);
    }
}
