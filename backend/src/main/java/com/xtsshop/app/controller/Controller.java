package com.xtsshop.app.controller;

import com.xtsshop.app.viewmodel.RouteList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping()
    public RouteList routeList(){
        return new RouteList();
    }
}
