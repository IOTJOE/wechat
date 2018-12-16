package com.baolishang.springboot.exceptions;

/**
 * Created by will on 2016/9/6.
 */
public class ArgumentException extends RuntimeException{
    public ArgumentException(String message) {
        super(message);
    }
}
