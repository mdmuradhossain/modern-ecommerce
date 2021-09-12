package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Brand;
import io.murad.modern.ecommerce.dto.BrandDto;
import io.murad.modern.ecommerce.exception.BrandNotFoundException;
import io.murad.modern.ecommerce.mapper.BrandMapper;
import io.murad.modern.ecommerce.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public void addBrand(BrandDto brandDto) throws IOException {
        brandRepository.save(brandMapper.mapToBrand(brandDto));
    }

    public BrandDto getBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException("Brand Not found"));
        log.info("Brand: "+brand.getBrandName());
        log.info("Brand from db");
        return brandMapper.mapToBrandDto(brand);
    }

    public List<BrandDto> getBrands() {
        log.info("Brand List");
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::mapToBrandDto).collect(Collectors.toList());
    }

    public void updateBrand(Long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException("Brand not found"));
        brand.setBrandName(brandDto.getName());
        brand.setBrandDescription(brand.getBrandDescription());
        brand.setBrandUrl(brandDto.getWebsite());
        brandRepository.save(brand);
        log.info("Brand Updated");
    }

    public void deleteBrand(Long id){
        brandRepository.deleteById(id);
        log.info("Brand Removed");
    }
}
