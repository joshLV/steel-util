/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 本接口调用方式为双向认证的https
 * @author log.yin
 * @version $Id: HttpsClient.java, v 0.1 2014年11月26日 下午2:02:26 log.yin Exp $
 */
public class HttpsClient {
    public static String post(String url, String truststorepath, String p12path, String passwd,
                              String requestBody) {
        KeyStore trustStore = null;
        KeyStore keyStore = null;
        FileInputStream trustStoreFile = null;
        FileInputStream keyStoreFile = null;

        String responseStr = "";
        SSLContext sslcontext = null;

        CloseableHttpClient httpclient = null;
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore = KeyStore.getInstance("PKCS12");
            trustStoreFile = new FileInputStream(new File(truststorepath));
            keyStoreFile = new FileInputStream(new File(p12path));
            trustStore.load(trustStoreFile, passwd.toCharArray());
            keyStore.load(keyStoreFile, passwd.toCharArray());

            sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, passwd.toCharArray())
                .loadTrustMaterial(trustStore).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            EntityBuilder entityBuilder = EntityBuilder.create().setContentEncoding("UTF-8")
                .setContentType(ContentType.APPLICATION_JSON);
            entityBuilder.setText(requestBody);
            httppost.setEntity(entityBuilder.build());

            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseStr = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (keyStoreFile != null) {
                    keyStoreFile.close();
                }
                if (trustStoreFile != null) {
                    trustStoreFile.close();
                }
                if (response != null) {
                    response.close();
                }
                if (httppost != null) {
                    httppost.releaseConnection();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (Exception e) {
            }
        }
        return responseStr;
    }
}
