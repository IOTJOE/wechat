package com.baolishang.springboot.utils;

import com.baolishang.springboot.storage.SmsConfigStorage;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.InputStream;
import java.net.URLDecoder;

/**
 * @Name
 * @Author Joe mailTo:jiangzhongke@baojinsuo.com
 * @Date 2016/11/1 上午11:25
 * @Desc
 */
public class HttpSender {
    public static String batchSend(SmsConfigStorage configStorage) throws Exception {
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(configStorage.getUri(), false);
            method.setURI(new URI(base, "HttpBatchSendSM", false));
            method.setQueryString(new NameValuePair[] {
                    new NameValuePair("account", configStorage.getAccount()),
                    new NameValuePair("pswd", configStorage.getPassword()),
                    new NameValuePair("mobile", configStorage.getMobile()),
                    new NameValuePair("needstatus", String.valueOf(configStorage.isNeedsStatus())),
                    new NameValuePair("msg", configStorage.getMessage()),
                    new NameValuePair("extno", configStorage.getExto()),
            });
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }

}
