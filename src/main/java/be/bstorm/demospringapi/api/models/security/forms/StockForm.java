package be.bstorm.demospringapi.api.models.security.forms;


import be.bstorm.demospringapi.dl.entities.Stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;




public record StockForm(

        @NotNull  // Vérifie que la valeur n'est pas nulle
        @PositiveOrZero // Vérifie que la quantité est >= 0
        int quantite_disponible)

{



    public Stock toStock(){
        return new Stock(this.quantite_disponible);
    }
}
