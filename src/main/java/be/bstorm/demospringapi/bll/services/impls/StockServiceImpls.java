package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.forms.StockForm;
import be.bstorm.demospringapi.bll.exceptions.stock.StockNotFoundException;
import be.bstorm.demospringapi.bll.services.StockService;
import be.bstorm.demospringapi.dal.repositories.StockRepository;
import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import be.bstorm.demospringapi.il.utils.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpls implements StockService {
    private final StockRepository stockRepository;



    @Override
    public Page<Stock> getStocks(List<SearchParam<Stock>> searchParams,Pageable pageable) {

        if (searchParams.isEmpty()){
            return stockRepository.findAll(pageable);
        }
        return stockRepository.findAll(
                Specification.allOf(
                        searchParams.stream()
                                .map(SearchSpecification::search)
                                .toList()
                ),
                pageable
        );
    }



    @Override
    public void insert(StockForm stockForm) {
        Stock stock = stockForm.toStock();
        stockRepository.save(stock);

    }

    @Override
    public void update(Long id , StockForm form) {
        Stock stock = stockRepository.findById(id).orElseThrow(()->new StockNotFoundException(HttpStatus.NOT_FOUND, "Post with id " + id + " not found"));
        stock.setQuantiteDisponible(form.quantite_disponible());


        stockRepository.save(stock);


    }

    @Override
    public void delete(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stockRepository.delete(stock);
    }

    @Override
    public Integer getStockByProduct(Long id) {
        return stockRepository.getQuantiteFromIdProduit(id).orElseThrow();

    }

    @Override
    public List<Stock> getStockByUser(Long id) {
        return stockRepository.findAllByUser(id).orElseThrow();
    }

    @Override
    public void deleteProductUser(Long idProduct, Long id) {
        stockRepository.deleteProductUser(idProduct,id);
    }
    public List<Stock> getLowStock(int threshold, Long id) {
        return stockRepository.findLowStock(threshold, id );
    }

}
