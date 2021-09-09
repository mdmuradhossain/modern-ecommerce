package io.murad.modern.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long id;
    private String code;
    private String name;
    private String price;
    private String description;
    private Integer stock;
    private String categoryName;
    private String brandName;
}
