package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.database.model.Product;
import io.murad.modern.ecommerce.dto.ProductRequest;
import io.murad.modern.ecommerce.dto.ProductResponse;
import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import io.murad.modern.ecommerce.mapper.ProductMapper;
import io.murad.modern.ecommerce.repository.CategoryRepository;
import io.murad.modern.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public void addProduct(ProductRequest productRequest) {
        Category category = categoryRepository.findByCategoryName(productRequest.getCategoryName());
        productRepository.save(productMapper.mapToProduct(productRequest,category));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToProductDto).collect(Collectors.toList());
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(()-> new ModernEcommerceException("Product not found"));
    }
}
