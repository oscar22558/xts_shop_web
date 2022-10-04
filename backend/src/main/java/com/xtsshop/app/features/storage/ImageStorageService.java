package com.xtsshop.app.features.storage;

import com.xtsshop.app.features.storage.config.StorageProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Qualifier("ImageStorageService")
public class ImageStorageService extends FileStorageService {
    public ImageStorageService(StorageProperties properties, Util util) {
        super(properties, util);
        this.root = Paths.get(properties.getImageRoot()).toAbsolutePath();
    }
}
