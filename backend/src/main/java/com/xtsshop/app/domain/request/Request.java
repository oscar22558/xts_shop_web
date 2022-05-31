package com.xtsshop.app.domain.request;

import com.xtsshop.app.db.entities.AppEntity;

public interface Request<E extends AppEntity> {
    E toEntity();
    E update(E original);
}
