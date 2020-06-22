package com.turkeymz.baseboot.controller;

import com.turkeymz.baseboot.annotation.AutoLog;
import com.turkeymz.baseboot.entity.APIResultBody;
import com.turkeymz.baseboot.exception.SysException;
import com.turkeymz.baseboot.exception.code.HttpExceptionCode;
import com.turkeymz.baseboot.util.SpringContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);


    @ApiOperation("测试普通API通信")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="path",name="name",dataType="String",required=true,value="名字",defaultValue="tom")
    })
    @GetMapping("/hello/{name}/{action}")
    @AutoLog(value = "sys say")
    public APIResultBody sayHello(@PathVariable("name") String name,@PathVariable("action") String action) throws Exception{
        System.out.println(SpringContextUtil.getHttpServletRequest());
        logger.info("input: {}",name);
        String result = "Hello " + name + ", here is base boot.Are you sure to "+ action;
        if("error".equals(name)){
            throw new SysException(HttpExceptionCode.SERVER_BUSY);
        }
        Thread.sleep(1000);
        logger.info("action: {}",action);
        return APIResultBody.success(result);
    }


}

