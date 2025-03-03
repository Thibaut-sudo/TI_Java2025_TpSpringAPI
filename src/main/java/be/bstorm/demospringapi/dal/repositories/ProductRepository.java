package be.bstorm.demospringapi.dal.repositories;


import be.bstorm.demospringapi.dl.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Product getById(Long id);
}
