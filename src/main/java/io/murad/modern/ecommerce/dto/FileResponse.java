package io.murad.modern.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
