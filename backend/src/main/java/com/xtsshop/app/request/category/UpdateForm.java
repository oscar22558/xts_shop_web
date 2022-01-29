package com.xtsshop.app.request.category;

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
    private Long parentId;
}
