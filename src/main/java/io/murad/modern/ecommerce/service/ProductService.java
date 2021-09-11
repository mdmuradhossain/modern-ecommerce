package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Brand;
import io.murad.modern.ecommerce.database.model.Category;
import io.murad.modern.ecommerce.database.model.Product;
import io.murad.modern.ecommerce.dto.ProductRequest;
import io.murad.modern.ecommerce.dto.ProductResponse;
import io.murad.modern.ecommerce.exception.FileStorageException;
import io.murad.modern.ecommerce.exception.ProductNotFoundException;
import io.murad.modern.ecommerce.mapper.ProductMapper;
import io.murad.modern.ecommerce.repository.BrandRepository;
import io.murad.modern.ecommerce.repository.CategoryRepository;
import io.murad.modern.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        product.setProductCode(RandomStringUtils.randomAlphanumeric(4));
        product.setProductName(productRequest.getName());
        product.setProductDescription(productRequest.getDescription());
        product.setProductPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setBestseller(productRequest.getBestseller());
        product.setCategory(category);
        product.setBrand(brand);
//        product.setProductImageUrl(provideFileDownloadUrlFromMultipart(productRequest.getFile()));
        productRepository.save(product);
        log.info("Product Saved.");
    }

    public List<ProductResponse> getAllProducts() {
        log.info("Get all products");
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToProductDto).collect(Collectors.toList());
    }

    public Product getProduct(Long id) {
        log.info("Get Product");
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public ProductResponse getProductResponse(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        log.info("Get Product");
        return productMapper.mapToProductDto(product);
    }

    public void updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setProductCode(RandomStringUtils.randomAlphanumeric(4));
        product.setProductName(productRequest.getName());
        product.setProductDescription(productRequest.getDescription());
        product.setStock(productRequest.getStock());
        product.setBestseller(productRequest.getBestseller());

        Category category = categoryRepository.findByCategoryName(productRequest.getCategoryName());
        Brand brand = brandRepository.findByBrandName(productRequest.getBrandName());

        product.setCategory(category);
        product.setBrand(brand);

        productRepository.save(product);
        log.info("Product Updated");
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        log.info("Product deleted");
    }

    public String provideFileDownloadUrlFromMultipart(MultipartFile file) throws FileStorageException {
        String fileName = fileStorageService.storeFile(file);
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName).toUriString();
    }
}
