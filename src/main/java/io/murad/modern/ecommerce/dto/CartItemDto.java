package io.murad.modern.ecommerce.dto;

import io.murad.modern.ecommerce.database.model.Cart;
import io.murad.modern.ecommerce.database.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private Long userId;
    private Integer quantity;
    private Product product;

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setUserId(cart.getUser().getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}
