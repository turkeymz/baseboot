package com.turkeymz.baseboot.controller;

import com.turkeymz.baseboot.entity.APIResultBody;
import com.turkeymz.baseboot.exception.SysException;
import com.turkeymz.baseboot.exception.code.HttpExceptionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/hello/{name}")
    public APIResultBody sayHello(@PathVariable("name") String name){

        logger.info("input: {}",name);

        String result = "Hello " + name + ", here is base boot.";

        if("error".equals(name)){
            throw new SysException(HttpExceptionCode.SERVER_BUSY);
        }

        return APIResultBody.success(result);
    }

}

