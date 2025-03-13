package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.OrderDTO;
import be.bstorm.demospringapi.api.models.security.forms.OrderForm;
import be.bstorm.demospringapi.bll.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Long userId, @RequestBody OrderForm orderForm) {
        return ResponseEntity.ok(orderService.createOrder(userId, orderForm));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
}