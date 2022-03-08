package com.xtsshop.app.controller;

import com.xtsshop.app.service.storage.FileStorageService;
import com.xtsshop.app.service.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("storage")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);
    private final StorageService service;

    public FileController(@Qualifier("FileStorageService") StorageService service){
        this.service = service;
    }

    @GetMapping()
    public List<String> all() throws IOException {
       return service.loadAll().map(path ->
            MvcUriComponentsBuilder.fromMethodName(
                FileController.class,
                "serveFile",
                path.getFileName().toString()
            ).build().toUri().toString()
        ).collect(Collectors.toList());
    }
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = service.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @PostMapping()
    public String handleFileUpload(
        @RequestParam("files") MultipartFile file
    ) {
        Path path = service.store(file);
        logger.info("------------------------FileController-----------------------------");
        logger.info(path.toString());
        logger.info(path.toAbsolutePath().toString());
        return service.url(path);

    }

}
