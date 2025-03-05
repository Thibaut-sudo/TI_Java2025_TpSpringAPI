package be.bstorm.demospringapi.api.models.security.dtos;



import be.bstorm.demospringapi.dl.entities.Warehouse;

public record WarehouseDTO(

        Long id,
        String nom,
        Long stockid) {



    public static WarehouseDTO fromWarehouse(Warehouse warehouse) {
       return new WarehouseDTO(warehouse.getId(),warehouse.getNom(), warehouse.getStock().getId());
    }
}
