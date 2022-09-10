package com.xtsshop.app.features.items.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class CreateItemRequest {

    private String name;
    private Float price;
    private String manufacturer;
    private MultipartFile image;
    private Long categoryId;
    private Integer stock;
    private Long brandId;

    public CreateItemRequest(String name, Float price, String manufacturer, MultipartFile image, Long categoryId, Integer stock, Long brandId) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.image = image;
        this.categoryId = categoryId;
        this.stock = stock;
        this.brandId = brandId;
    }

}
