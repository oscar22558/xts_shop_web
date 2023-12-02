package com.xtsshop.app.features.routeindex;

import com.xtsshop.app.features.routeindex.models.RoutePresentationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRouteIndexController {
    private Logger logger = LoggerFactory.getLogger(ApiRouteIndexController.class);
    @GetMapping()
    public RoutePresentationModel routeList(){
        logger.info("api route index controler called");
        return new RoutePresentationModel();
    }
}
