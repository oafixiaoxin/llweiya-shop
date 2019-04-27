package com.llweiya.apt.inner;

import com.llweiya.apt.AnnotationProcessor;

import javax.annotation.processing.RoundEnvironment;

/**
 * Created by llweiya on 19/04/27.
 * 注解处理器接口
 */
public interface IProcessor {
    void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor);
}
