package com.baolishang.springboot.beans;

/**
 * Created by will on 2016/9/6.
 */
public enum ResponseCode {

    success("success", "成功"),
    login_required("login_first","请先登录"),
    validation_code_not_correct("validation_code_not_correct", "验证码不正确"),
    validation_code_not_found("validation_code_not_found", "验证码已过期,请重新获取"),
    password_format_error("password_format_error", "密码格式错误"),
    net_failed("net_failed", "网络延迟，提交失败"),
    captcha_error("withdraw_amount_error", "验证码错误"),
    login_failed("login_failed", "用户名或密码错误"),
    mobile_error("mobile_error","手机错误"),
    name_error("name_error","姓名长度有误"),
    bind_wechat_already("bind_already","微信号已绑定,无需重复绑定"),
    bind_phone_already("bind_already","手机号已绑定,无需重复绑定"),
    unknown_error("unknown_error","未知错误"),
    outtime_error("outtime_error","页面超时,请刷新重试"),
    openid_error("openid_error","未识别到微信账户,请刷新重试"),
    sina_realname_verified_null("realname_verify_first","请在新浪实名注册");

    private String code;
    private String 	desc;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
