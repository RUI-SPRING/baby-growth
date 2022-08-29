package com.feign.common;

import com.feign.common.enums.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;

@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public String ErrorHandler(AuthException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }

    @ExceptionHandler(Exception.class)
    public Result Execption(Exception e) {
        log.error("未知异常！", e);
        return Result.error(ResultMsgEnum.SERVER_BUSY.getCode(),ResultMsgEnum.SERVER_BUSY.getMessage());
    }
}