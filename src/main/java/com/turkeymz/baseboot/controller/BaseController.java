package com.turkeymz.baseboot.controller;

import com.turkeymz.baseboot.entity.APIResultBody;
import com.turkeymz.baseboot.exception.SysException;
import com.turkeymz.baseboot.exception.code.HttpExceptionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class BaseController {

    @GetMapping("/hello/{name}")
    public APIResultBody sayHello(@PathVariable("name") String name){
        String result = "Hello " + name + ", here is base boot.";
        if("error".equals(name)){
            throw new SysException(HttpExceptionCode.SERVER_BUSY);
        }

        return APIResultBody.success(result);
    }

}

