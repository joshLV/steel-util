/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util.httpclient;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 发送普通Content-Type为text/html的get/post请求
 * @author log.yin
 * @version $Id: HttpSend.java, v 0.1 2014年12月26日 下午5:43:47 log.yin Exp $
 */
public class HttpSend {
    public static String sendGet(String uri) {
        String response = "";
        try {
            HttpGet httpget = new HttpGet();
            httpget.setURI(new URI(uri));
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(httpget, new ResponseHandler<String>() {
                public String handleResponse(HttpResponse response) throws ClientProtocolException,
                                                                   IOException {
                    StatusLine statusLine = response.getStatusLine();
                    final HttpEntity entity = response.getEntity();
                    String body = null;
                    if (200 == statusLine.getStatusCode()) {
                        HttpEntity dealEntity = response.getEntity();
                        body = EntityUtils.toString(dealEntity, "UTF-8");
                        EntityUtils.consume(dealEntity);
                    } else if (statusLine.getStatusCode() >= 300) {
                        EntityUtils.consume(entity);
                        throw new HttpResponseException(statusLine.getStatusCode(), statusLine
                            .getReasonPhrase());
                    }
                    return body;
                }
            });
        } catch (Exception e) {
        }
        return response;
    }
}
