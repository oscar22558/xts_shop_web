package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.service.storage.ImageStorageService;
import com.xtsshop.app.service.storage.StorageProperties;
import com.xtsshop.app.service.storage.StorageService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ImageModel implements AbstractViewModel {

    private String imgUrl;
    @Autowired
    @Qualifier("ImageStorageService")
    private StorageService storageService;
    public ImageModel(String imgUrl) {
        this.imgUrl = storageService.url(imgUrl);
    }

    public static ImageModel from(Image entity){
        return new ImageModel(entity.getPath());
    }
}