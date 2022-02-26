package com.xtsshop.app.request;

import com.xtsshop.app.db.entities.AppEntity;

public interface Form<E extends AppEntity> {
    E toEntity();
    E update(E original);
}
