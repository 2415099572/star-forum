package com.starforum.handler;

import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        int a = 1;
        e.printStackTrace();
        return new Result(false, StateCode.ERROR, "程序出错: " + e.getMessage());

    }
}


