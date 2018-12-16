package com.baolishang.springboot.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.SignatureException;

/**
 * MD5签名处理核心文件
 * */

public class MD5Utils {

    /**
     * 签名字符串
     *
     * @param text
     *            需要签名的字符串
     * @param key
     *            密钥
     * @param charset
     *            编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String charset) throws Exception {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }
    /**
     * 签名字符串
     *
     * @param text
     *            需要签名的字符串
     * @param key
     *            密钥
     * @param charset
     *            编码格式
     * @return 签名结果
     * @deprecated 无替代方法
     */
    @Deprecated
	public static String sign(String text, PrivateKey key, String charset) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     * 签名字符串
     *
     * @param text
     *            需要签名的字符串
     * @param sign
     *            签名结果
     * @param key
     *            密钥
     * @param charset
     *            编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String charset)
                                                                                      throws Exception {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, charset));
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    public static String getMD5(String source){
        byte[] tmpbytes = null;
        try{
            tmpbytes = source.getBytes("utf-8");
        }catch(Exception ex){

        }
        return getMD5(tmpbytes);
    }
    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = {       // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'};
        try
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance( "MD5" );
            md.update( source );
            byte tmp[] = md.digest();          // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2];   // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0;                                // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {          // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i];                 // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf];            // 取字节中低 4 位的数字转换
            }
            s = new String(str);                                 // 换后的结果转换为字符串

        }catch( Exception e )
        {
            e.printStackTrace();
        }
        return s;
    }
}
