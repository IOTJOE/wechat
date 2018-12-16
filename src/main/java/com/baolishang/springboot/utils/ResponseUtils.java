package com.baolishang.springboot.utils;

import com.baolishang.springboot.beans.CodeMessageJson;
import com.baolishang.springboot.beans.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by will on 2016/9/6.
 */
public class ResponseUtils {

    public static ResponseEntity restResponse(String code,String message,HttpStatus status){
        return new ResponseEntity<>(
                new CodeMessageJson(code,message),status
        );
    }

    public static ResponseEntity restResponse(ResponseCode code, HttpStatus status){
        return new ResponseEntity<>(
                new CodeMessageJson(code.getCode(), code.getDesc()), status
        );
    }

    public static ResponseEntity restResponse(Object data, String code, String message, HttpStatus status){
        return new ResponseEntity<>(
                new CodeMessageJson(data,code, message), status
        );
    }

    public static ResponseEntity restResponse(Object data, Object page, String code, String message, HttpStatus status){

        return new ResponseEntity<>(
                new CodeMessageJson(data, page, code, message), status
        );
    }
}
