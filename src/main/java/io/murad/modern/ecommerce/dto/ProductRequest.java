package io.murad.modern.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer stock;
    private Boolean bestseller;
    private String categoryName;
    private String brandName;
//    private MultipartFile file;
}
