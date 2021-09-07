package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.dto.BrandDto;
import io.murad.modern.ecommerce.mapper.BrandMapper;
import io.murad.modern.ecommerce.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public void addBrand(BrandDto brandDto, MultipartFile file) throws IOException {
        brandRepository.save(brandMapper.mapToBrand(brandDto,file));
    }
}
