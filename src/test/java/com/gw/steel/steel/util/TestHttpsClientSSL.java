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
        String url = "http://10.15.89.77:9090/AccService/checkuser";
        String truststorepath = "d:/tester.truststore";
        String p12path = "d:/tester.p12";
        String passwd = "123456";
        String requestBody = "{\"uname\":\"测试\"}";
        String result = HttpsClient.post(url, truststorepath, p12path, passwd, requestBody);
        System.out.println("result: " + result);

    }

    private static void bind() {
        String url = "http://10.15.89.77:9090/AccService/xcscbind";
        String truststorepath = "d:/tester.truststore";
        String p12path = "d:/tester.p12";
        String passwd = "123456";
        String requestBody = "{\"uname\":\"测试\",\"mobile\":\"15921866000\",\"xcid\":\"888888\",\"reqdata\":\"测试\"}";
        String result = HttpsClient.post(url, truststorepath, p12path, passwd, requestBody);
        System.out.println("result: " + result);

    }
}
