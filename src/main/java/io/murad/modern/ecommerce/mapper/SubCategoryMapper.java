package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.database.model.SubCategory;
import io.murad.modern.ecommerce.dto.SubCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {


    @Mapping(target = "id",source="subCategoryDto.id")
    @Mapping(target = "subCategoryName", source = "subCategoryDto.name")
    @Mapping(target = "description",source = "subCategoryDto.description")
    @Mapping(target = "category",source = "category")
    public SubCategory mapToSubCategory(SubCategoryDto subCategoryDto, Category category);

    @Mapping(target = "name", source = "subCategory.subCategoryName")
    public SubCategoryDto mapSubCategoryToDto(SubCategory subCategory);

}
