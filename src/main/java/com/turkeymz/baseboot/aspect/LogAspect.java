package com.turkeymz.baseboot.aspect;

import com.alibaba.fastjson.JSONObject;
import com.turkeymz.baseboot.annotation.AutoLog;
import com.turkeymz.baseboot.entity.APISysLog;
import com.turkeymz.baseboot.util.IPUtils;
import com.turkeymz.baseboot.util.SpringContextUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 创建切点
    @Pointcut("@annotation(com.turkeymz.baseboot.annotation.AutoLog)")
    public void logPointCut() {

    }

    // 编写切面逻辑
    @Around("logPointCut()")
    public Object requestLog(ProceedingJoinPoint point) throws Throwable {
        // 启动计时器
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 方法执行
        Object result = point.proceed();
        // 关闭计时器,打印日志
        stopWatch.stop();
        printLog(point, stopWatch.getTotalTimeMillis());
        return result;
    }

    private void printLog(ProceedingJoinPoint point, long time) {
        // 获取信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        AutoLog log = method.getAnnotation(AutoLog.class);
        HttpServletRequest request = SpringContextUtil.getHttpServletRequest();
        // 保存信息
        APISysLog apiSysLog = new APISysLog(log.value(),
                this.getReqestParams(request,point),
                IPUtils.getIpAddr(request),
                time);
        // 记录日志
        logger.info("请求IP: {}",apiSysLog.getIp());
        logger.info("请求方法: {}",apiSysLog.getLogValue());
        logger.info("请求参数: {}",apiSysLog.getRequest());
        logger.info("执行时间: {} ms",apiSysLog.getDuration());
    }

    private String getReqestParams(HttpServletRequest request, JoinPoint joinPoint) {
        String httpMethod = request.getMethod();
        String params = "";
        if ("POST".equals(httpMethod) || "PUT".equals(httpMethod) || "PATCH".equals(httpMethod)) {
            Object[] paramsArray = joinPoint.getArgs();
            params = JSONObject.toJSONString(paramsArray);
        } else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 请求的方法参数值
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params += "  " + paramNames[i] + ": " + args[i];
                }
            }
        }
        return params;
    }
}
