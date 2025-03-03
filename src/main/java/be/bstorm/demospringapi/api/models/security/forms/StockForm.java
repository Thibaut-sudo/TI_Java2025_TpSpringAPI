package be.bstorm.demospringapi.api.models.security.forms;

import be.bstorm.demospringapi.api.models.security.dtos.StockDTO;
import be.bstorm.demospringapi.dl.entities.Stock;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public record StockForm(
        @NotBlank @Size(max = 30)
        int quantite_disponible,
        @NotBlank @Size(max = 30)
        Long product_id,
        @NotBlank @Size(max = 30)
        Long stock_id) {


    public Stock toStock(){
        return new Stock();
    }
}
