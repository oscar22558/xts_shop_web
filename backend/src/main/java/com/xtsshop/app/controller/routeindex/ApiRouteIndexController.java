package com.xtsshop.app.controller.routeindex;

import com.xtsshop.app.controller.routeindex.models.RoutePresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRouteIndexController {
    @GetMapping()
    public RoutePresentationModel routeList(){
        return new RoutePresentationModel();
    }
}
