package io.murad.modern.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDto {
    private String productName;
    private Integer  quantity;
    private Double price;
    private Long productId;
    private Long userId;
}
