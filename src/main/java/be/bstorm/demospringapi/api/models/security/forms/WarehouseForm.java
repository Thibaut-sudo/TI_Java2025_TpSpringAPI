package be.bstorm.demospringapi.api.models.security.forms;


import be.bstorm.demospringapi.dl.entities.Warehouse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WarehouseForm (


        @NotBlank @Size(max = 50)
        String nom ,

        Long stock_id) {

    public Warehouse toWarehouse() {
        return new Warehouse(this.nom,this.stock_id, null);
    }
}

