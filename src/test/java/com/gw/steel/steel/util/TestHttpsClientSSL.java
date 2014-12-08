/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util;

import com.gw.steel.steel.util.httpclient.HttpsClient;

/**
 * 
 * @author log.yin
 * @version $Id: TestHttpsClientSSL.java, v 0.1 2014年11月26日 下午2:18:49 log.yin Exp $
 */
public class TestHttpsClientSSL {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        check();
        
        bind();
    }

    /**
     * 
     */
    private static void check() {
        String url = "https://10.15.108.5:8440/AccService-xc/checkuser";
        String truststorepath = "d:/tester.truststore";
        String p12path = "d:/tester.p12";
        String passwd = "123456";
        String requestBody = "{\"uname\":\"测试\"}";
        String result = HttpsClient.post(url, truststorepath, p12path, passwd, requestBody);
        System.out.println("result: " + result);

    }

    private static void bind() {
        String url = "https://10.15.108.5:8440/AccService/xcscbind";
        String truststorepath = "d:/tester.truststore";
        String p12path = "d:/tester.p12";
        String passwd = "123456";
        String requestBody = "{\"uname\":\"测试\",\"mobile\":\"15921866000\",\"xcid\":\"888888\",\"truename\":\"老罗\", \"idcard\":\"436950321596315745\",\"opendate\":\"2014-11-26\",\"opentype\":\"1\",\"source\":\"1\"}";
        String result = HttpsClient.post(url, truststorepath, p12path, passwd, requestBody);
        
        System.out.println("result: " + result);

    }
}
