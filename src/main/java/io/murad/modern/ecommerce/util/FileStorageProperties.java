package io.murad.modern.ecommerce.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
//@ConfigurationProperties("storage")
public class FileStorageProperties {
    private String fileUploadLocation = "uploads";
}
