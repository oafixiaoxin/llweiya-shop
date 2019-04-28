package com.llweiya.ysx.starchef.aop;

import android.util.Log;

import com.llweiya.ysx.starchef.common.application.LlweiyaApp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CheckLoginAspect {
    public CheckLoginAspect() {
        DaggerCheckLoginComponent.builder()
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    @Around("execution(@com.example.annotation.aspect.CheckLogin * *(..)) && @annotation(checkLogin)")
    public void aroundJoinPoint(ProceedingJoinPoint point) throws Throwable {
        Log.e("llweiya >>> ", "do something");
        point.proceed();
    }
}
