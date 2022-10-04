package com.xtsshop.app.features.storage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Qualifier("FileStorageService")
public class FileStorageService implements StorageService{

    protected Path root;
    protected Path envRoot;
    protected Util util;
    protected StorageProperties properties;
    public FileStorageService(StorageProperties properties, Util util) {
        this.properties = properties;
        this.root = Paths.get(properties.getEnvRoot());
        this.envRoot = Paths.get(properties.getEnvRoot());
        this.util = util;
    }

    @Override
    public Path store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String extension = util.getExtension(file);
            if(extension == null)
                throw new StorageException("Cannot resolve file extension.");
            String uniqueFileName = UUID.randomUUID().toString()+ "." + extension;
            Path destFile = this.root.resolve(
                            Paths.get(uniqueFileName));
            Path destFileAbsolutePath = destFile
                    .normalize().toAbsolutePath();
            if (!destFileAbsolutePath.getParent().equals(this.root.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destFileAbsolutePath,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return destFile;
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return root.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public void delete(String filename){
        Path path = root.resolve(filename);
        Path absolutePath = path.normalize().toAbsolutePath();
        if (!absolutePath.getParent().equals(this.root.toAbsolutePath())) {
            throw new StorageException(
                    "Cannot delete file outside current directory.");
        }
        FileSystemUtils.deleteRecursively(absolutePath.toFile());
    }

    @Override
    public void init() {
        try {
            if(Files.notExists(envRoot)){
                Files.createDirectories(envRoot);
            }
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
