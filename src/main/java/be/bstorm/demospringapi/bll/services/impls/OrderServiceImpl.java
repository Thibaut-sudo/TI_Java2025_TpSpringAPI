package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.dtos.OrderDTO;
import be.bstorm.demospringapi.api.models.security.forms.OrderForm;
import be.bstorm.demospringapi.bll.exceptions.user.UserNotFoundException;
import be.bstorm.demospringapi.bll.services.CharacterService;
import be.bstorm.demospringapi.bll.services.OrderService;
import be.bstorm.demospringapi.dal.repositories.OrderRepository;
import be.bstorm.demospringapi.dal.repositories.UserRepository;
import be.bstorm.demospringapi.dl.entities.Order;
import be.bstorm.demospringapi.dl.entities.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CharacterService characterService;

    @Override
    public OrderDTO createOrder(Long userId, OrderForm orderForm) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
        if (orderForm.getTotalAmount() <= 0) {
            throw new IllegalArgumentException("Le montant de la commande doit être supérieur à zéro.");
        }
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(orderForm.getTotalAmount());
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        characterService.assignCharactersToUser(userId, orderForm.getTotalAmount());
        return OrderDTO.fromOrder(savedOrder);
    }

    @Override
    public List<OrderDTO> getUserOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        return orderRepository.findByUser(user).stream()
                .map(OrderDTO::fromOrder)
                .collect(Collectors.toList());
    }
}