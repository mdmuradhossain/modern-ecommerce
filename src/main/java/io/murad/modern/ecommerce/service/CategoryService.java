package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.dto.CategoryDto;
import io.murad.modern.ecommerce.exception.CategoryNotFoundException;
import io.murad.modern.ecommerce.mapper.CategoryMapper;
import io.murad.modern.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public void addCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.mapToCategory(categoryDto));
        log.info("Category Saved: "+categoryDto.getName());
    }

    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not Found."));
        log.info("Get Category: "+category.getCategoryName());
        return categoryMapper.mapCategoryToDto(category);
    }

    public List<CategoryDto> getAllCategories() {
        log.info("Categories...");
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapCategoryToDto).collect(Collectors.toList());
    }

    public void updateCategory(Long id,CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category Not found"));
        category.setCategoryName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        log.info("Category Removed");
        categoryRepository.deleteById(id);
    }


}
