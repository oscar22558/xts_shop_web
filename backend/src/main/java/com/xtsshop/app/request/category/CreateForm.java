package com.xtsshop.app.request.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateForm {
    private String name;
    private long parentId;
}
