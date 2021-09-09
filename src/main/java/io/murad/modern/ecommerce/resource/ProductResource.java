package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.dto.ProductRequest;
import io.murad.modern.ecommerce.dto.ProductResponse;
import io.murad.modern.ecommerce.exception.FileStorageException;
import io.murad.modern.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/products")
public class ProductResource {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) throws FileStorageException {
        productService.addProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
