package com.xtsshop.app.features.storage;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @GetMapping(value = "/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] serveFile(@PathVariable String filename) throws IOException {
        Path path = service.load(filename);
        logger.info("Load resource: " + path);
        File file = path.toFile();
        InputStream inputStream = new FileInputStream(file);
        return IOUtils.toByteArray(inputStream);
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
