package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.dto.FileResponse;
import io.murad.modern.ecommerce.exception.FileStorageException;
import io.murad.modern.ecommerce.service.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/assets")
@AllArgsConstructor
public class FileResource {

    private final FileStorageService fileStorageService;

    @PostMapping()
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) throws FileStorageException {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();
        return new ResponseEntity<FileResponse>(new FileResponse(fileName,fileDownloadUrl,file.getContentType(),file.getSize()), HttpStatus.OK);
    }
}
