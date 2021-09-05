package io.murad.modern.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Double price;
    private Integer quantity;
    private Long orderId;
    private Long productId;
    
}
