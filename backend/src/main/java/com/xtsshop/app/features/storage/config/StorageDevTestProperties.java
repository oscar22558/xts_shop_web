package com.xtsshop.app.features.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import java.nio.file.Path;
import java.nio.file.Paths;

@ConfigurationProperties("storage-config-dev-test")
@Profile({"test", "dev"})
public class StorageDevTestProperties {

    private String imageFolder = "images";

    protected Path getRootPath(){
        return Paths.get("");
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
