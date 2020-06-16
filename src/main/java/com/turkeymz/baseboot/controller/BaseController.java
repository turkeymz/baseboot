package com.turkeymz.baseboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class BaseController {

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name){
        return "Hello " + name + ", here is base boot.";
    }

}

