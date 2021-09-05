package io.murad.modern.ecommerce.dto;

import io.murad.modern.ecommerce.database.model.Order;
import io.murad.modern.ecommerce.database.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDto {
    private Long id;
    private User user;
    private Double totalPrice;

    public PlaceOrderDto(Order order) {
        this.setId(order.getId());
        this.setUser(order.getUser());
        this.setTotalPrice(order.getTotalPrice());
    }
}
