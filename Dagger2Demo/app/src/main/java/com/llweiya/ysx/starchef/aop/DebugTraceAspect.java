package com.llweiya.ysx.starchef.aop;

import android.util.Log;

import com.llweiya.ysx.starchef.common.plugin.StopWatch;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class DebugTraceAspect {
    private static final String POINTCUT_METHOD = "execution(@com.llweiya.ysx.starchef.common.plugin.DebugTrace * * (..))";
    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.llweiya.ysx.starchef.common.plugin *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {

    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedWithDebugTrace() {

    }

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedWithDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object object = joinPoint.proceed();
        stopWatch.stop();

        Log.e(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));

        return object;
    }

    private static String buildLogMessage(String methodName, long duration) {
        StringBuilder builder = new StringBuilder();
        builder.append("Gintonic -- > ");
        builder.append(methodName);
        builder.append(" --> ");
        builder.append("[");
        builder.append(duration);
        builder.append("ms");
        builder.append("]");

        return builder.toString();
    }
}
