package com.xtsshop.app.features.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import java.nio.file.Path;
import java.nio.file.Paths;

@ConfigurationProperties("storage-config-production")
@Profile("production")
public class StorageProductionProperties extends StorageDevTestProperties {
    private String fileServerFolder = "file-server";

    @Override
    protected Path getRootPath(){
        return Paths.get("").toAbsolutePath().getParent().resolve(fileServerFolder);
    }
}
