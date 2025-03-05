package be.bstorm.demospringapi.dal.repositories;


import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.dl.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {
}
