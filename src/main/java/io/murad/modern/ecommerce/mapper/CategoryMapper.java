package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public Category mapToCategory(CategoryDto categoryDto);



    @Mapping(target = "id",source="category.id")
    @Mapping(target = "name",source="category.categoryName")
    @Mapping(target = "description",source="category.description")
    @Mapping(target="products",ignore = true)
    public CategoryDto mapCategoryToDto(Category category);
}
