package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.api.models.security.dtos.OrderDTO;
import be.bstorm.demospringapi.api.models.security.forms.OrderForm;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(Long userId, OrderForm orderForm);
    List<OrderDTO> getUserOrders(Long userId);
}