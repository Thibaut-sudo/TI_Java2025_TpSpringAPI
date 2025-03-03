package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {

    Optional<Stock> findById(Long aLong);






}
