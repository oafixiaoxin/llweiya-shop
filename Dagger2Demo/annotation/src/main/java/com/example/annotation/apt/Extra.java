package com.example.annotation.apt;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by llweiya on 19/04/27.
 */
@Retention(CLASS)
@Target(FIELD)
public @interface Extra {
    String value();
}
