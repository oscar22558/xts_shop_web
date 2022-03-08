package com.xtsshop.app.service.storage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.nio.file.Paths;

@Service
@Qualifier("ImageStorageService")
public class ImageStorageService extends FileStorageService {
    public ImageStorageService(StorageProperties properties, Util util) {
        super(properties, util);
        this.root = Paths.get(properties.getImageRoot());
    }
}
