package com.xtsshop.app.domain.request;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
public class ImageRequest implements Request<Image> {
    private MultipartFile image;
    private Item item;
    private String path;
    private String fileName;
    private String extension;
    @Override
    public Image toEntity() {
        long now = Calendar.getInstance().getTimeInMillis();
        Image image = new Image();
        image.setPath(path);
        image.setFileName(fileName);
        image.setExtension(extension);
        image.setItem(item);
        image.setCreatedAt(new Date(now));
        image.setUpdatedAt(new Date(now));
        return image;
    }

    @Override
    public Image update(Image original) {
        long now = Calendar.getInstance().getTimeInMillis();
        original.setPath(path);
        original.setFileName(fileName);
        original.setExtension(extension);
        original.setItem(original.getItem().getId() != item.getId() ? item : original.getItem());
        original.setUpdatedAt(new Date(now));
        return original;
    }
}
