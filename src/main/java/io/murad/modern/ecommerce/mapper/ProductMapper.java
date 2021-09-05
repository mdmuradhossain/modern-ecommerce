package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Product;
import io.murad.modern.ecommerce.dto.ProductRequest;
import io.murad.modern.ecommerce.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", source = "productRequest.id")
    public Product mapToProduct(ProductRequest productRequest);
    public ProductResponse mapToProductDto(Product product);
}
