package com.gw.steel.steel.util.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * AES 加密算法
 * @author Administrator
 *
 */

public class AES {
    private static Log          logger     = LogFactory.getLog(AES.class);

    //加密key与向量长度为16位
    private static final int    KEY_LENGTH = 16;
    private static final String ENCODING   = "UTF-8";

    /**
     * iv have the same value of key
     */
    public static String encrypt(String key, String str) throws Exception {
        return encrypt(key, key, str);
    }

    public static String encrypt(String key, String iv, String str) throws Exception {
        String pkey;
        String piv;
        pkey = padRight(key);
        piv = padRight(iv);
        String encryStr = doEncrypt(pkey, piv, str);
        if (encryStr != null) {
            return base64Replace(encryStr);
        }
        return null;
    }

    /**
     * iv have the same value of key
     */
    public static String decrypt(String key, String str) throws Exception {
        return decrypt(key, key, str);
    }

    public static String decrypt(String key, String iv, String str) throws Exception {
        String replacedStr = base64Restore(str);
        String pkey;
        String piv;
        pkey = padRight(key);
        piv = padRight(iv);
        return doDecrypt(pkey, piv, replacedStr);
    }

    private static String doEncrypt(String key, String iv, String str) throws Exception {
        if (key == null || iv == null || str == null) {
            logger.info("key/iv/str is null");
            return null;
        }
        if (key.length() != KEY_LENGTH) {
            logger.info("key length is not " + KEY_LENGTH);
            return null;
        }

        byte[] dataBytes = str.getBytes(ENCODING);
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(ENCODING), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"
        int plaintextLength = dataBytes.length;
        int blockSize = cipher.getBlockSize();
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes(ENCODING));
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivParam);
        byte[] encrypted = cipher.doFinal(plaintext);

        //使用BASE64做转码功能，同时能起到2次加密的作用
        String encryptedStringWithBase64 = Base64.encodeBase64String(encrypted);
        return encryptedStringWithBase64;
    }

    private static String doDecrypt(String key, String iv, String str) throws Exception {
        if (key == null || iv == null || str == null) {
            logger.info("key/iv/str is null");
            return null;
        }
        if (key.length() != KEY_LENGTH) {
            logger.info("key length is not " + KEY_LENGTH);
            return null;
        }

        byte[] raw = key.getBytes(ENCODING);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        IvParameterSpec ivParams = new IvParameterSpec(iv.getBytes(ENCODING));
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParams);
        byte[] encrypted1 = Base64.decodeBase64(str);
        byte[] originalByte = cipher.doFinal(encrypted1);
        return new String(originalByte, ENCODING).trim();
    }

    /**
     * 对字符串不满足指定长度的在右侧补空格
     */
    private static String padRight(String source) {
        if (source == null) {
            return null;
        }
        int curLength = source.getBytes().length;
        if (source != null && curLength > KEY_LENGTH)
            source = subStringByte(source);
        String newString = "";
        int cutLength = KEY_LENGTH - source.getBytes().length;
        for (int i = 0; i < cutLength; i++)
            newString += " ";
        String preString = source + newString;
        return preString;
    }

    private static String subStringByte(String source) {
        while (source.getBytes().length > KEY_LENGTH)
            source = source.substring(0, source.length() - 1);
        return source;
    }

    /**
     * 从普通字符串转换为适用于URL的Base64编码字符串
     */
    private static String base64Replace(String normalString) {
        return normalString.replace('+', '*').replace('/', '-').replace('=', '.');
    }

    /**
     * 从普通字符串转换为适用于URL的Base64编码字符串
     */
    private static String base64Restore(String base64String) {
        return base64String.replace('.', '=').replace('*', '+').replace('-', '/');
    }

    public static void main(String[] args) throws Exception {
        String source = "eghjij1561@#$%^&*eghjij1561";
        String s1 = encrypt("jf.gw.com.cnjf. .com.cn", "xc-jf.gw.com.cn124", source);
        String s2 = decrypt("jf.gw.com.cnjf. .com.cn", "xc-jf.gw.com.cn124", s1);

        System.err.println(source);
        System.err.println(s1);
        System.err.println(s2);
    }
}