package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Brand;
import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.database.model.Product;
import io.murad.modern.ecommerce.dto.ProductRequest;
import io.murad.modern.ecommerce.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "id", source = "productRequest.id")
    @Mapping(target = "category",source="category")
    @Mapping(target = "brand",source = "brand")
    public Product mapToProduct(ProductRequest productRequest, Category category, Brand brand);

    @Mapping(target = "price", source = "product.productPrice")
    @Mapping(target = "name", source = "product.productName")
    public ProductResponse mapToProductDto(Product product);
}
