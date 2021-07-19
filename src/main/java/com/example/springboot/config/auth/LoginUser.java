package com.example.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //이 애너테이션이 생성될 수 있는 위치 지정. PARAMETER로 지정했으므로 메서드의 파라미터로 선언된 객체에만 사용할 수 있음.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
