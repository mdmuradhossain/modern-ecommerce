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
import java.util.List;

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

    @GetMapping(path = "/{id}")
    public ResponseEntity<BrandDto> getBrand(@PathVariable("id") Long id) {
        BrandDto brandDto = brandService.getBrand(id);
        return new ResponseEntity<>(brandDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<BrandDto>> allBrands() {
        return new ResponseEntity<>(brandService.getBrands(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editBrand(@PathVariable("id") Long id, @RequestBody BrandDto brandDto) {
        brandService.updateBrand(id, brandDto);
        return new ResponseEntity<>("Brand Updated.", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeBrand(@PathVariable("id") Long id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<>("Brand Removed.", HttpStatus.OK);
    }
}
