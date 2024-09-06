package com.ddd.arch.validation.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({
        ElementType.TYPE
})
@Retention(RUNTIME)
@Documented
public @interface ValueObject {

    Type type() default Type.PLAIN_TYPE;

    enum Type {
        PLAIN_TYPE,
        WRAPPER_TYPE
    }
}
