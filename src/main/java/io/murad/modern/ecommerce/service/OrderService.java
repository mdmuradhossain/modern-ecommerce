package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Order;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.PlaceOrderDto;
import io.murad.modern.ecommerce.exception.OrderNotFoundException;
import io.murad.modern.ecommerce.repository.CartRepository;
import io.murad.modern.ecommerce.repository.OrderItemRepository;
import io.murad.modern.ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;

    public Order saveOrder(PlaceOrderDto orderDto, User user, String sessionID){
        Order order = getOrderFromDto(orderDto, user, sessionID);
        return orderRepository.save(order);
    }

    private Order getOrderFromDto(PlaceOrderDto orderDto, User user, String sessionID) {
        Order order = new Order(orderDto, user,sessionID);
        return order;
    }

    public List<Order> listOrders(User user) {
        List<Order> orderList = orderRepository.findAllByUserOrderByCreatedDateDesc(user);
        return orderList;
    }

    public Order getOrder(Long orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }
}
