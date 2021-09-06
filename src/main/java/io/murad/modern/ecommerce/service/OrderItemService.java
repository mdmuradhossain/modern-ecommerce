package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.OrderItem;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void addOrderedProducts(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }

}
