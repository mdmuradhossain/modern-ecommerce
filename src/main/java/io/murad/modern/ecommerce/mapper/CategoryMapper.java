package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mapping(target = "subcategories", ignore = true)
    @Mapping(target = "products",ignore = true)
    @Mapping(target = "categoryName", source = "categoryDto.name")
    public Category mapToCategory(CategoryDto categoryDto);



    @Mapping(target = "id",source="category.id")
    @Mapping(target = "name",source="category.categoryName")
    @Mapping(target = "description",source="category.description")
    public CategoryDto mapCategoryToDto(Category category);
}
