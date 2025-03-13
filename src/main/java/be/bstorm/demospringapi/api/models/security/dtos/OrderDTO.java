package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.dl.entities.Order;

import java.time.LocalDateTime;

public record OrderDTO(
        Long id,
        Long userId,
        double totalAmount,
        LocalDateTime orderDate
) {
    public static OrderDTO fromOrder(Order order) {
        return new OrderDTO(order.getId(), order.getUser().getId(), order.getTotalAmount(), order.getOrderDate());
    }

}