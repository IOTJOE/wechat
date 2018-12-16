package com.baolishang.springboot.exceptions;

/**
 * Created by will on 2016/9/6.
 */
public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message){
        super(message);
    }
}

