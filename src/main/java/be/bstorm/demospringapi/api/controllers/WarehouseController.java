package be.bstorm.demospringapi.api.controllers;



import be.bstorm.demospringapi.api.models.security.dtos.StockDTO;
import be.bstorm.demospringapi.api.models.security.dtos.WarehouseDTO;
import be.bstorm.demospringapi.api.models.security.forms.WarehouseForm;
import be.bstorm.demospringapi.bll.services.WarehouseService;

import be.bstorm.demospringapi.dl.entities.Warehouse;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {


        private final WarehouseService warehouseService;
        @GetMapping
        public ResponseEntity<List<WarehouseDTO>> getWarehouse(
                @RequestParam Map<String,String> params,
                @RequestParam(required = false, defaultValue = "0") int page,
                @RequestParam(required = false, defaultValue = "10") int size,
                @RequestParam(required = false, defaultValue = "id") String sort
        ) {
            List<SearchParam<Warehouse>> searchParams = SearchParam.create(params);

            List<WarehouseDTO> warehouseList = warehouseService.getWarehouse(
                            searchParams,
                            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))
                    ).stream()
                    .map(warehouse -> {
                        WarehouseDTO warehouseDTO = WarehouseDTO.fromWarehouse(warehouse);
                        return warehouseDTO;
                    })
                    .toList();

            return ResponseEntity.ok(warehouseList);
        }

        @PreAuthorize("isAuthenticated()")
        @PostMapping
        public ResponseEntity<WarehouseDTO> createWarehouse(
                @Valid @RequestBody WarehouseForm form

        ){
            warehouseService.insert(form);
            return ResponseEntity.noContent().build();
        }

        @PreAuthorize("isAuthenticated()")
        @DeleteMapping("/{id}")
        public ResponseEntity<WarehouseDTO> deleteWarehouse(
                @PathVariable Long id

        ){
            warehouseService.delete(id);
            return ResponseEntity.noContent().build();
        }


}
