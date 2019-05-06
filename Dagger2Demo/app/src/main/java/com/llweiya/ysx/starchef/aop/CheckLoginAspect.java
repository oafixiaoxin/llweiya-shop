package com.llweiya.ysx.starchef.aop;

import android.util.Log;

import com.example.annotation.aspect.CheckLogin;
import com.llweiya.ysx.starchef.business.user.model.UserInfoModel;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.lpmas.apt.LWRouter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.inject.Inject;

@Aspect
public class CheckLoginAspect {

    @Inject
    UserInfoModel userInfoModel;

    public CheckLoginAspect() {
        DaggerCheckLoginComponent.builder()
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    @Around("execution(@com.example.annotation.aspect.CheckLogin * *(..)) && @annotation(checkLogin)")
    public void aroundJoinPoint(ProceedingJoinPoint point, CheckLogin checkLogin) throws Throwable {
        if (userInfoModel.isGuest()) {
            LWRouter.go(LlweiyaApp.getCurrentActivity(), RouterConfig.NEWLOGIN);
            return;
        }
        point.proceed();
    }
}
