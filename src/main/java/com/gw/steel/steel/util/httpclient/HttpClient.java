/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author log.yin
 * @version $Id: HttpClient.java, v 0.1 2014年11月26日 上午10:16:00 log.yin Exp $
 */
public class HttpClient {
    /**
     * post json request
     * 
     * @param url
     * @param requestBody
     * @return
     */
    public static String post(String url, String requestBody) {
        String responseStr = "";
        try {
            StringEntity stringEntity = new StringEntity(requestBody);
            stringEntity.setContentType("application/json");

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(stringEntity);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try {
                    responseStr = EntityUtils.toString(entity);
                } finally {
                    response.close();
                    httpPost.releaseConnection();
                }
            }
        } catch (Exception e) {
        }
        return responseStr;
    }
}
