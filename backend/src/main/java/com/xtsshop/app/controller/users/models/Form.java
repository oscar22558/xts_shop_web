package com.xtsshop.app.controller.users.models;

import com.xtsshop.app.db.entities.AppEntity;

public interface Form<R extends Request<E>, E extends AppEntity> {
    R toRequest();
}
