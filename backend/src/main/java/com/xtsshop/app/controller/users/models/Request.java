package com.xtsshop.app.controller.users.models;

import com.xtsshop.app.db.entities.AppEntity;

public interface Request<E extends AppEntity> {
    E toEntity();
    E update(E original);
}
