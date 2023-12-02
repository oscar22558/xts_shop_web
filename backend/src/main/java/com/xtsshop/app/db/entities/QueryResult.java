package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.NamedNativeQuery;

public interface QueryResult {
    long getId();
    String getUsername();
    String getRole();
}
