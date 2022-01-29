package com.xtsshop.app.request.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class CreateForm {
    private String name;
    private float price;
    private String manufacturer;
    private long categoryId;
}
