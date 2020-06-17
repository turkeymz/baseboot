package com.turkeymz.baseboot.exception;

import com.turkeymz.baseboot.exception.code.BaseException;

public class SysException extends RuntimeException{

    // 异常编码
    protected String errorCode;
    // 异常详情
    protected String errorMsg;

    public SysException() {
        super();
    }

    public SysException(BaseException baseException) {
        super(baseException.getErrorCode());
        this.errorCode = baseException.getErrorCode();
        this.errorMsg = baseException.getErrorMsg();
    }

    public SysException(BaseException baseException, Throwable cause) {
        super(baseException.getErrorCode(), cause);
        this.errorCode = baseException.getErrorCode();
        this.errorMsg = baseException.getErrorMsg();
    }

    public SysException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public SysException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public SysException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
