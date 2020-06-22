package com.turkeymz.baseboot.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SysLog
 * @Description: TODO
 * @Author Richard
 * @Date 2020/6/22
 * @Version V1.0
 **/
public class APISysLog {
    // log 类型
    private String logValue;
    // 请求
    private String request;
    // IP地址
    private String ip;
    // 耗时
    private long duration;

    public APISysLog() {

    }

    public APISysLog(String logValue, String request, String ip, long duration) {
        this.logValue = logValue;
        this.request = request;
        this.ip = ip;
        this.duration = duration;
    }

    public String getLogValue() {
        return logValue;
    }

    public void setLogValue(String logValue) {
        this.logValue = logValue;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
