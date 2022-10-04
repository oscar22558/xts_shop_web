package com.xtsshop.app.features.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

@ConfigurationProperties("storage-config")
public class StorageProperties {

    private String root = "storage";
    private String imageFolder = "images";

    protected Path getRootPath(){
        return Paths.get(root).toAbsolutePath();
    }
    public String getEnvRoot(){
        return getRoot();
    }
    public String getRoot() {
        return getRootPath().toString();
    }
    public String getImageRoot() {
        return getRoot() + "/" + imageFolder;
    }
}
