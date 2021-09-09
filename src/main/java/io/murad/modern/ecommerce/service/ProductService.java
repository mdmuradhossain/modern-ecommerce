package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Brand;
import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.database.model.Product;
import io.murad.modern.ecommerce.dto.ProductRequest;
import io.murad.modern.ecommerce.dto.ProductResponse;
import io.murad.modern.ecommerce.exception.FileStorageException;
import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import io.murad.modern.ecommerce.exception.ProductNotFoundException;
import io.murad.modern.ecommerce.mapper.ProductMapper;
import io.murad.modern.ecommerce.repository.BrandRepository;
import io.murad.modern.ecommerce.repository.CategoryRepository;
import io.murad.modern.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final FileStorageService fileStorageService;

    public void addProduct(ProductRequest productRequest) throws FileStorageException {
        Category category = categoryRepository.findByCategoryName(productRequest.getCategoryName());
        Brand brand = brandRepository.findByBrandName(productRequest.getBrandName());
//        productRepository.save(productMapper.mapToProduct(productRequest,category,brand));
        Product product = new Product();
        product.setProductCode(productRequest.getCode());
        product.setProductName(productRequest.getName());
        product.setProductDescription(productRequest.getDescription());
        product.setProductPrice(productRequest.getPrice());
        product.setBestseller(productRequest.getBestseller());
        product.setCategory(category);
        product.setBrand(brand);
        product.setProductImageUrl(provideFileDownloadUrlFromMultipart(productRequest.getFile()));
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToProductDto).collect(Collectors.toList());
    }

    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.mapToProductDto(product);
    }

    public String provideFileDownloadUrlFromMultipart(MultipartFile file) throws FileStorageException {
        String fileName = fileStorageService.storeFile(file);
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName).toUriString();
    }
}
