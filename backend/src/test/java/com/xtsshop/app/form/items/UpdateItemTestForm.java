package com.xtsshop.app.form.items;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.annotation.Nullable;

@Setter
@Getter
@NoArgsConstructor
public class UpdateItemTestForm {

    @Nullable
    private String name;
    @Nullable
    private Float price;
    @Nullable
    private String manufacturer;
    @Nullable
    private Long categoryId;
    @Nullable
    private Integer stock;
}
