package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.database.model.SubCategory;
import io.murad.modern.ecommerce.dto.SubCategoryDto;
import io.murad.modern.ecommerce.exception.CategoryNotFoundException;
import io.murad.modern.ecommerce.mapper.SubCategoryMapper;
import io.murad.modern.ecommerce.repository.CategoryRepository;
import io.murad.modern.ecommerce.repository.SubCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SubCategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper;

    public void addSubCategory(SubCategoryDto subCategoryDto) {
        Category category = categoryRepository.findByCategoryName(subCategoryDto.getSubCategoryName());
        subCategoryRepository.save(subCategoryMapper.mapToSubCategory(subCategoryDto, category));
        log.info("Sub category saved: " + subCategoryDto.getSubCategoryName());
    }

    public SubCategoryDto getSubCategory(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Sub category not found"));
        log.info("Get SubCategory");
        return subCategoryMapper.mapSubCategoryToDto(subCategory);
    }

    public List<SubCategoryDto> getAllSubCategories() {
        return subCategoryRepository.findAll()
                .stream()
                .map(subCategoryMapper::mapSubCategoryToDto)
                .collect(Collectors.toList());
    }

    public void updateSubCategory(Long id, SubCategoryDto subCategoryDto) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Sub category not found"));
        subCategory.setSubCategoryName(subCategoryDto.getName());
        subCategory.setDescription(subCategoryDto.getDescription());
        Category category = categoryRepository.findByCategoryName(subCategoryDto.getSubCategoryName());
        subCategory.setCategory(category);
        subCategoryRepository.save(subCategory);
        log.info("Sub category updated");
    }

    public void deleteSubCategory(Long id) {
        subCategoryRepository.deleteById(id);
        log.info("Sub category deleted");
    }
}
