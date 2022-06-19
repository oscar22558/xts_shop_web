package com.xtsshop.app.controller;

import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
import com.xtsshop.app.domain.service.storage.StorageService;
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
@RequestMapping("storage")
public class FileController {
    private StorageService service;
    private FilePathToUrlConverter filePathToUrlConverter;
    public FileController(
            @Qualifier("FileStorageService") StorageService service,
            FilePathToUrlConverter filePathToUrlConverter
    ){
        this.service = service;
        this.filePathToUrlConverter = filePathToUrlConverter;
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
        return filePathToUrlConverter.getUrl(path);
    }
}
