package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ImageModel implements AbstractViewModel {

    private String imgPath;
    @Autowired
    private FilePathToUrlConverter filePathToUrlConverter;

    public ImageModel(String imgPath) {
        this.imgPath = filePathToUrlConverter.getUrl(imgPath);
    }

    public static ImageModel from(Image entity){
        return new ImageModel(entity.getPath());
    }
}