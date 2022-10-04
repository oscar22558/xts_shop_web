package com.xtsshop.app.features.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("storage/images")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(FileController.class);
    private StorageService service;
    private UriPathConverter uriPathConverter;
    public FileController(
            @Qualifier("ImageStorageService") StorageService service,
            UriPathConverter uriPathConverter
    ){
        this.service = service;
        this.uriPathConverter = uriPathConverter;
    }

    @GetMapping()
    public List<String> all() throws IOException {
        return service.loadAll().map(path ->
                uriPathConverter.getUri(path)
        ).collect(Collectors.toList());
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = service.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
    }

    @PostMapping()
    public String handleFileUpload(
        @RequestParam("files") MultipartFile file
    ) {
        logger.info("Handling file upload...");

        Path path = service.store(file);
        return uriPathConverter.getUri(path);
    }
}
