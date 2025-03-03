package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.StockDTO;
import be.bstorm.demospringapi.bll.services.StockService;
import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.SearchControls;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;
    @GetMapping
    public ResponseEntity<List<StockDTO>> getStocks(
            @RequestParam Map<String,String>params,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "name") String sort
            ) {
        List<SearchParam<Stock>> searchParams = SearchParam.create(params);
        List<StockDTO>stock = stockService.getStocks(
                searchParams,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))
        ).stream()
                .map(StockDTO::fromStock)
                .toList();


       return ResponseEntity.ok(stock);
    }
}
