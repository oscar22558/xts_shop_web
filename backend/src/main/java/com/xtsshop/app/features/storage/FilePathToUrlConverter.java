package com.xtsshop.app.features.storage;

import com.xtsshop.app.features.storage.config.StorageDevTestProperties;
import org.springframework.stereotype.Component;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FilePathToUrlConverter {
    private Path file;
    private Path storageRootFolder;
    private String urlRoot;

    public FilePathToUrlConverter(StorageDevTestProperties properties){
        this.storageRootFolder = Paths.get(properties.getEnvRoot());
        this.urlRoot = "storage";
    }

    public String getUrl(String filePath) {
        Path file = Paths.get(filePath);
        return getUrl(file);
    }

    public String getUrl(Path file) {
        this.file = file;
        return toUrl();
    }

    private String toUrl(){
        return urlRoot + "/" + getPathRelativeToFolderRoot().toString().replace("\\", "/");
    }

    private Path getPathRelativeToFolderRoot(){
        return storageRootFolder.relativize(file);
    }
}
