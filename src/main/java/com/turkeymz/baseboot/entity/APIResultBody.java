package com.turkeymz.baseboot.entity;

import com.turkeymz.baseboot.exception.code.BaseException;

/*
 * API统一返回对象
 */
public class APIResultBody {
    // API状态码，成功：0
    private String code;
    // API状态详情
    private String message;
    // API结果，失败则无结果
    private Object result;

    public APIResultBody() {
    }

    public APIResultBody(BaseException ex) {
        this.code = ex.getErrorCode();
        this.message = ex.getErrorMsg();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static APIResultBody success(Object result) {
        APIResultBody rb = new APIResultBody();
        rb.setCode("0");
        rb.setMessage("调用成功!");
        rb.setResult(result);
        return rb;
    }

    public static APIResultBody error(BaseException ex) {
        APIResultBody rb = new APIResultBody();
        rb.setCode(ex.getErrorCode());
        rb.setMessage(ex.getErrorMsg());
        rb.setResult(null);
        return rb;
    }

    public static APIResultBody error(String errorCode,String errorMsg) {
        APIResultBody rb = new APIResultBody();
        rb.setCode(errorCode);
        rb.setMessage(errorMsg);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return "APIResultBody {" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
