package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.dl.entities.Stock;

public record StockDTO(
        Long id,
        int quantiteDisponible,
        Long produitId,
        Long userId) {


    public static StockDTO fromStock(Stock stock) {
        return new StockDTO(stock.getId(), stock.getQuantiteDisponible(), stock.getProduit().getId(), stock.getUser().getId());
    }
}
