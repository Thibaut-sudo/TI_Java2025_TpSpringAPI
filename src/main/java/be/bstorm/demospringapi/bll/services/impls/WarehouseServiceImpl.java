package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.forms.WarehouseForm;
import be.bstorm.demospringapi.bll.exceptions.warehouse.WarehouseNotFoundException;
import be.bstorm.demospringapi.bll.services.WarehouseService;
import be.bstorm.demospringapi.dal.repositories.StockRepository;
import be.bstorm.demospringapi.dal.repositories.WarehouseRepository;
import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.dl.entities.Warehouse;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import be.bstorm.demospringapi.il.utils.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final StockRepository stockRepository;



    @Override
    public Page<Warehouse> getWarehouse(List<SearchParam<Warehouse>> searchParams, Pageable pageable) {
        if (searchParams.isEmpty()){
            return warehouseRepository.findAll(pageable);
        }
        return warehouseRepository.findAll(
                Specification.allOf(
                        searchParams.stream()
                                .map(SearchSpecification::search)
                                .toList()
                ),
                pageable
        );
    }




    @Override
    public void insert(WarehouseForm warehouseForm) {
        Stock stock = stockRepository.findById(warehouseForm.stock_id())
                .orElseThrow(() -> new IllegalArgumentException("Stock non trouvé avec ID : " + warehouseForm.stock_id()));


        Warehouse warehouse = new Warehouse(warehouseForm.nom(), stock, null);
        warehouseRepository.save(warehouse);
    }


        @Override
        public void delete(Long id) {
            Warehouse warehouse = warehouseRepository.findById(id)
                    .orElseThrow(()->new WarehouseNotFoundException(HttpStatus.NOT_FOUND, "Warehouse with id " + id + " not found"));
            warehouseRepository.delete(warehouse);
        }
    }
