package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Brand;
import io.murad.modern.ecommerce.dto.BrandDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class BrandMapper {


    @Mapping(target = "brandName", source = "brandDto.name")
    @Mapping(target = "brandLogo", expression = "java(getFile(file))")
    @Mapping(target = "brandDescription", source = "brandDto.description")
    public abstract Brand mapToBrand(BrandDto brandDto,MultipartFile file) throws IOException;


    public String getFile(MultipartFile file) throws IOException {
        Brand brand = new Brand();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        brand.setBrandLogo(fileName);

        Path uploadPath = Paths.get("uploads/");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path imagePath = uploadPath.resolve(file.getOriginalFilename());
            System.out.println(imagePath.toFile().getAbsolutePath());
            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("could not save");
        }
        return fileName;
    }
}
