package com.xtsshop.app.form;

import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.request.Request;

public interface Form<R extends Request<E>, E extends AppEntity> {
    R toRequest();
}
