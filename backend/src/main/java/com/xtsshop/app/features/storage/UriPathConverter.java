package com.xtsshop.app.features.storage;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UriPathConverter {
    private Path storageRootFolder;

    public UriPathConverter(){
        this.storageRootFolder = Paths.get("").toAbsolutePath();
    }

    public Path getPath(String uri) {
        String path = storageRootFolder.toAbsolutePath() + uri;
        return Paths.get(path);
    }

    public String getUri(Path path){
        Path absolutePath = path.toAbsolutePath();
        return storageRootFolder.relativize(absolutePath).toString();
    }

    public String getUri(String path){
        return getUri(Paths.get(path));
    }
}
