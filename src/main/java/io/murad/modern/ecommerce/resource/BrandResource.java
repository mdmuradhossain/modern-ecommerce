package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.dto.BrandDto;
import io.murad.modern.ecommerce.service.BrandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/brands")
public class BrandResource {

    private final BrandService brandService;

    @PostMapping()
    public ResponseEntity<?> createBrand(@RequestBody BrandDto brandDto, @RequestParam("file") MultipartFile file) throws IOException {
        brandService.addBrand(brandDto, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
