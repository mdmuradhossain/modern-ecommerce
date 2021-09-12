package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.dto.SubCategoryDto;
import io.murad.modern.ecommerce.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/subcategories")
@AllArgsConstructor
public class SubCategoryResource {

    private final SubCategoryService subCategoryService;

    @PostMapping()
    public ResponseEntity<?> createSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        subCategoryService.addSubCategory(subCategoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "SubCategory",key = "#id")
    public ResponseEntity<SubCategoryDto> getSubCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(subCategoryService.getSubCategory(id), HttpStatus.OK);
    }

    @GetMapping()
    @Cacheable(value = "SubCategories")
    public ResponseEntity<List<SubCategoryDto>> getSubCategories() {
        return new ResponseEntity<>(subCategoryService.getAllSubCategories(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editSubCategory(@PathVariable("id") Long id, @RequestBody SubCategoryDto subCategoryDto) {
        subCategoryService.updateSubCategory(id, subCategoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeSubCategory(@PathVariable("id") Long id) {
        subCategoryService.deleteSubCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
