package com.xtsshop.app.domain.request.items;

import lombok.Getter;
import java.util.Optional;

@Getter
public class UpdateItemRequest {

    private Long id;
    private Optional<Long> categoryId;
    private Optional<String> name;
    private Optional<Float> price;
    private Optional<String> manufacturer;
    private Optional<Integer> stock;
    private Optional<Long> brandId;

    public void setId(Long id){
        this.id = id;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = Optional.ofNullable(categoryId);
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public void setPrice(Float price) {
        this.price = Optional.ofNullable(price);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = Optional.ofNullable(manufacturer);
    }

    public void setStock(Integer stock) {
        this.stock = Optional.ofNullable(stock);
    }

    public void setBrandId(Long brandId) {
        this.brandId = Optional.ofNullable(brandId);
    }


}
