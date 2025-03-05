package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.api.models.security.forms.WarehouseForm;
import be.bstorm.demospringapi.dl.entities.Warehouse;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface WarehouseService {
    Page<Warehouse> getWarehouse(List<SearchParam<Warehouse>> searchParams, Pageable pageable);


    void insert(@Valid WarehouseForm form);


    void delete(Long id);
}
