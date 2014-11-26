/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("version", "1.0");
        requestBody.put("inputCharset", "1");
        requestBody.put("signType", "1");
        requestBody.put("clientId", "1");
        requestBody.put("xc_user_id", "aaaa");
        requestBody.put("currentStep", "11");
        requestBody.put("currentStepDesc", "步骤描述");
        requestBody.put("mobile", "1525465654");
        requestBody.put("userName", "gaowei");
        requestBody.put("custName", "gaowei");
        requestBody.put("operatorTime", "201411142215");
        requestBody.put("OSN", "0001");
        requestBody.put("signMsg", "signMsg");

        String url = "http://10.15.201.25:8883/xc-aas/accounts/postOpenAcctStep";
        String resp = HttpClient.post(url, requestBody);
        System.out.println("response data: " + resp);

    }

}
