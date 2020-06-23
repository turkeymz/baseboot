package com.turkeymz.baseboot.aspect;

import com.turkeymz.baseboot.entity.APIResultBody;
import com.turkeymz.baseboot.util.SpringContextUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private StopWatch stopWatch;

    // 创建切点
    @Pointcut("@annotation(com.turkeymz.baseboot.annotation.AutoLog)")
    public void logPointCut() {

    }
    // 开始切面逻辑
    @Before(value = "logPointCut()")
    public void before(JoinPoint joinPoint) {
        stopWatch = new StopWatch();
        stopWatch.start();
        //  获取方法相关
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取请求
        HttpServletRequest request = SpringContextUtil.getHttpServletRequest();
        //获取请求参数
        String requestBody = this.getParams(joinPoint,signature);

        logger.info("请求信息:{}, 请求类型:{}, IP:{},请求方法:{}", request.getRequestURL(), request.getMethod(),
                request.getRemoteAddr(), signature.getDeclaringTypeName());
        logger.info("请求参数:{}", requestBody);
    }
    // 结束切面逻辑
    @AfterReturning(value = "logPointCut()", returning = "apiResultBody")
    public void after(APIResultBody apiResultBody) {
        stopWatch.stop();
        logger.info("响应参数:{}",apiResultBody.toString());
        logger.info("耗时: {} ms",stopWatch.getTotalTimeMillis());

    }

    private String getParams(JoinPoint joinPoint,MethodSignature signature){
        StringBuffer requestBody = new StringBuffer();
        // 获取请求参数
        String[] paramNames = ((MethodSignature) signature).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        int paramLength = null == paramNames ? 0 : paramNames.length;
        if (paramLength == 0) {
            requestBody.append("{}");
        } else {
            requestBody.append("[");
            for (int i = 0; i < paramLength - 1; i++) {
                requestBody.append(paramNames[i]).append("=").append(paramValues[i]).append(", ");
            }
            requestBody.append(paramNames[paramLength - 1]).append("=").append(paramValues[paramLength - 1]).append("]");
        }
        return requestBody.toString();
    }

}