/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
    */
    public static String post(String url, String requestBody) throws ClientProtocolException,
                                                             IOException {
        String responseStr = "";
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
        return responseStr;
    }

    public static String get(String url) throws ClientProtocolException, IOException {
        String responseStr = "";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                responseStr = EntityUtils.toString(entity);
            } finally {
                response.close();
                httpGet.releaseConnection();
            }
        }
        return responseStr;
    }
}
