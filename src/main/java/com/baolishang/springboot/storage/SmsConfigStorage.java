package com.baolishang.springboot.storage;

/**
 * @Name
 * @Author Joe mailTo:jiangzhongke@baojinsuo.com
 * @Date 2016/10/31 下午4:34
 * @Desc
 */
public class SmsConfigStorage {
    /**
     *
     * @param url 应用地址，类似于http://ip:port/msg/
     * @param account 账号
     * @param pswd 密码
     * @param mobile 手机号码，多个号码使用","分割
     * @param msg 短信内容
     * @param needstatus 是否需要状态报告，需要true，不需要false
     * @return 返回值定义参见HTTP协议文档
     * @throws Exception
     */
    protected volatile String uri;
    protected volatile String account;
    protected volatile String password;
    protected volatile String mobile;
    protected volatile boolean needsStatus = true;
    protected volatile String message;
    protected volatile String exto = null;
    protected volatile String key = null;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isNeedsStatus() {
        return needsStatus;
    }

    public void setNeedsStatus(boolean needsStatus) {
        this.needsStatus = needsStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExto() {
        return exto;
    }

    public void setExto(String exto) {
        this.exto = exto;
    }

    public SmsConfigStorage(String uri, String account, String password,String key) {
        this.uri = uri;
        this.account = account;
        this.password = password;
        this.key = key;
    }

    @Override
    public String toString() {
        return "SmsConfigStorage{" +
                "uri='" + uri + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", needsStatus=" + needsStatus +
                ", message='" + message + '\'' +
                ", exto='" + exto + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
