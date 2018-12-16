package com.baolishang.springboot.exceptions;


import com.baolishang.springboot.beans.ResponseCode;
import org.springframework.http.HttpStatus;

/**
 * Created by will on 2016/9/6.
 */
public class BaseException extends RuntimeException {

    private String code;
    private String message;
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BaseException() {
    }

    public BaseException(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public BaseException(ResponseCode code, HttpStatus status) {
        this.code = code.getCode();
        this.message = code.getDesc();
        this.status = status;
    }
}
