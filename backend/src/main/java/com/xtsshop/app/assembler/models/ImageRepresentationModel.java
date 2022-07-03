package com.xtsshop.app.assembler.models;

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
public class ImageRepresentationModel implements AbstractRepresentationModel {

    private String imgPath;
    @Autowired
    private FilePathToUrlConverter filePathToUrlConverter;

    public ImageRepresentationModel(String imgPath) {
        this.imgPath = filePathToUrlConverter.getUrl(imgPath);
    }

    public static ImageRepresentationModel from(Image entity){
        return new ImageRepresentationModel(entity.getPath());
    }
}