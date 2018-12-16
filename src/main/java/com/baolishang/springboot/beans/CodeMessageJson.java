package com.baolishang.springboot.beans;

/**
 * Created by will on 2016/9/6.
 */
public class CodeMessageJson {
    private Object data;
    private Object pageParams;
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getPageParams() {
        return pageParams;
    }

    public void setPageParams(Object pageParams) {
        this.pageParams = pageParams;
    }

    public CodeMessageJson() {
    }

    public CodeMessageJson(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeMessageJson(Object data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public CodeMessageJson(Object data, Object pageParams, String code, String message) {
        this.data = data;
        this.pageParams = pageParams;
        this.code = code;
        this.message = message;
    }
}
