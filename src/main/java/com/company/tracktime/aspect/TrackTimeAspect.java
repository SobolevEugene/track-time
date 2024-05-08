package com.company.tracktime.aspect;

import com.company.tracktime.model.MethodType;
import com.company.tracktime.service.SaveTimeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Order(0)
public class TrackTimeAspect {

    Map<String, Long> syncMethodsRunTime = new HashMap<>();
    Map<String, Long> asyncMethodsRunTime = new HashMap<>();


    SaveTimeService saveTimeService;

    @Autowired
    public TrackTimeAspect(SaveTimeService saveTimeService) {
        this.saveTimeService = saveTimeService;
    }

    @Pointcut("@annotation(com.company.tracktime.annotation.TrackTime)")
    public void trackTime() {
    }

    @Pointcut("@annotation(com.company.tracktime.annotation.TrackAsyncTime)")
    public void trackAsyncTime() {
    }

    @Before(value = "trackTime()")
    public void beforeTrackTime(JoinPoint joinPoint) {
        syncMethodsRunTime.put(joinPoint.getSignature().getName(), System.currentTimeMillis());
    }

    @After(value = "trackTime()")
    public void afterTrackTime(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        if (syncMethodsRunTime.get(methodName) != null) {
            saveTimeService.saveMethodTimeExecution(methodName,
                    System.currentTimeMillis() - syncMethodsRunTime.get(methodName),
                    MethodType.SYNC);
        }
        syncMethodsRunTime.remove(methodName);
    }


    @Before(value = "trackAsyncTime()")
    public void beforeTrackAsyncTime(JoinPoint joinPoint) {
        asyncMethodsRunTime.put(joinPoint.getSignature().getName(), System.currentTimeMillis());
    }

    @After(value = "trackAsyncTime()")
    public void afterTrackAsyncTime(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        if (asyncMethodsRunTime.get(methodName) != null) {
            saveTimeService.saveMethodTimeExecution(methodName,
                    System.currentTimeMillis() - asyncMethodsRunTime.get(methodName),
                    MethodType.ASYNC);
        }
        asyncMethodsRunTime.remove(methodName);
    }

}
