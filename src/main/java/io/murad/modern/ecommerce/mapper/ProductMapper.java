package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Product;
import io.murad.modern.ecommerce.dto.ProductRequest;
import io.murad.modern.ecommerce.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    public Product mapToProduct(ProductRequest productRequest);
    public ProductResponse mapToProductDto(Product product);
}
