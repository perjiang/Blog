package com.jx.blog.Controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = AuthorizationException.class)
    public String AuthorizationExceptionHandler(){
        return "403";
    }
}
