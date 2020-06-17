package com.turkeymz.baseboot.handler;

import com.turkeymz.baseboot.entity.APIResultBody;
import com.turkeymz.baseboot.exception.SysException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SysExceptionHandler {

    /**
     * 全局异常处理
     */
    @ExceptionHandler(value = SysException.class)
    @ResponseBody
    public APIResultBody handleException(SysException e) {
        return APIResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }

}
