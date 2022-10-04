package com.xtsshop.app.db.entities.builder;

import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.helpers.DateTimeHelper;

import java.sql.Date;

public class ImageBuilder extends AppEntity {
    private String uri;
    private String fileName;
    private String extension;
    private Item item;

    public ImageBuilder setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public ImageBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ImageBuilder setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public ImageBuilder setItem(Item item) {
        this.item = item;
        return this;
    }
    public Image build(){
        Date now = new DateTimeHelper().now();
        Image img = new Image();
        img.setCreatedAt(now);
        img.setUpdatedAt(now);
        img.setUri(uri);
        img.setDescription(fileName);
        img.setExtension(extension);
        img.setItem(item);
        return img;
    }
}
