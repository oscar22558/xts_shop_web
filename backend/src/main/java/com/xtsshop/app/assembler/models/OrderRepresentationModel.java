package com.xtsshop.app.assembler.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class OrderRepresentationModel implements AbstractRepresentationModel {
    private AddressRepresentationModel address;
    private UserRepresentationModel user;
    private String orderStatus;
    private Collection<OrderedItemRepresentationModel> items;
}
