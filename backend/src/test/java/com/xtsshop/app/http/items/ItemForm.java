package com.xtsshop.app.http.items;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.annotation.Nullable;

@Setter
@Getter
@NoArgsConstructor
public class ItemForm {

    @Nullable
    private String name;
    @Nullable
    private Float price;
    @Nullable
    private String manufacturer;
    @Nullable
    private Long categoryId;
}
