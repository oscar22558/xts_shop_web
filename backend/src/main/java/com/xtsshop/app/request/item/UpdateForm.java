package com.xtsshop.app.request.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class UpdateForm {
    private long id;
    @Nullable
    private String name;
    @Nullable
    private Float price;
    @Nullable
    private String manufacturer;
    @Nullable
    private Long categoryId;
}
