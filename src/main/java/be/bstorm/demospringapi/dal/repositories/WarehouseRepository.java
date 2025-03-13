package be.bstorm.demospringapi.dal.repositories;


import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.dl.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {

    //modifier le stock d'un entrepot
    @Modifying
    @Query("update Warehouse w set w.stock = null where w.stock.id = :id")
    void update(long id);
}
