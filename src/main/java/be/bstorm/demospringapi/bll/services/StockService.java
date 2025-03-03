package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StockService {
    Page<Stock> getStocks(List<SearchParam<Stock>> searchParams, Pageable pageable);
}
