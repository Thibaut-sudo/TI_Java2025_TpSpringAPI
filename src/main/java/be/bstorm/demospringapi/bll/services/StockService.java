package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.api.models.security.forms.StockForm;
import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

public interface StockService {
    Page<Stock> getStocks(List<SearchParam<Stock>> searchParams, Pageable pageable);
    void insert(StockForm stockForm);

    void update(Long id , @Valid StockForm form);

    void delete(Long id);

    Integer getStockByProduct(Long id);

    List<Stock> getStockByUser(Long id);
}
