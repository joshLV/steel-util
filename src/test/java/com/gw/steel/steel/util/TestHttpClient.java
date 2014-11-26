/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util;

import com.gw.steel.steel.util.httpclient.HttpClient;

/**
 * 
 * @author log.yin
 * @version $Id: TestHttpClient.java, v 0.1 2014年11月26日 上午10:25:17 log.yin Exp $
 */
public class TestHttpClient {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {

        String url = "http://10.15.201.25:8883/xc-aas/accounts/postOpenAcctStep";
        String requestBody = "{\"version\":\"1.0\",\"inputCharset\":\"1\",\"OSN\":\"0001\",\"signMsg\":\"1\"}";
        String resp = HttpClient.post(url, requestBody);
        System.out.println("response data: " + resp);

    }

}
