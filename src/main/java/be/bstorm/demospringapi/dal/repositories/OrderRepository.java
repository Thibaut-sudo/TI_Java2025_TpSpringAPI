package be.bstorm.demospringapi.dal.repositories;

import be.bstorm.demospringapi.dl.entities.Order;
import be.bstorm.demospringapi.dl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

}