package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {

    Optional<Stock> findById(Long aLong);


    @Query ("SELECT s.quantiteDisponible FROM Stock s WHERE s.produit.id = :id")
    Optional<Integer> getQuantiteFromIdProduit(Long id);

    @Query ("SELECT s FROM Stock s WHERE s.user.id = :id")
    Optional<List<Stock>>  findAllByUser(Long id);
}
