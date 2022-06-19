package com.xtsshop.app.domain.request.items.images;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UpdateItemImageRequest {
    private Long itemId;
    private MultipartFile image;

    public UpdateItemImageRequest(Long itemId, MultipartFile image) {
        this.itemId = itemId;
        this.image = image;
    }

}
