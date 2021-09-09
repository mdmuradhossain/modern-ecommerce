package io.murad.modern.ecommerce.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@Setter
public class FileStorageProperties {
    private final String fileUploadLocation;
}
