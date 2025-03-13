package be.bstorm.demospringapi.dal.repositories;


import be.bstorm.demospringapi.dl.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Modifying
    @Query("delete from Product p where p.id = :id")
    void deleteById(Long id);


    Product getById(Long id);

    <T> Page<Product> findAll(Specification<T> tSpecification, Pageable pageable);
}
