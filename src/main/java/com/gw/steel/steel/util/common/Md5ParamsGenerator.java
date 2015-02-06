/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2015 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util.common;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author log.yin
 * @version $Id: Md5ParamsGenerator.java, v 0.1 2015年2月3日 下午7:25:16 log.yin Exp $
 */
public class Md5ParamsGenerator {
    /**
     * key1value1key2value2key3value3...
     * only append the key-value is not blank
     * @param map
     * @return
     */
    public static String generateMd5Params(Map<String, String> map) {
        return generateMd5Params(map, false);
    }

    /**
     * key1value1key2value2key3value3...
     * 
     * @param map
     * @param containsEmptyString: if or not the value is blank, also append the key and value
     * @return
     */
    public static String generateMd5Params(Map<String, String> map, boolean containsEmptyString) {
        StringBuilder signString = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                signString.append(entry.getKey()).append(entry.getValue());
                continue;
            }
            if (containsEmptyString) {
                signString.append(entry.getKey()).append("");
            }
        }
        return signString.toString();
    }

    /**
     * key1=value1&key2=value2&key3=value3...
     * 
     * @param map
     * @param keyLable: key-name
     * @return
     */
    public static String generateHttpGetParams(Map<String, String> map) {
        StringBuilder requstParams = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            requstParams.append(entry.getKey()).append("=")
                .append(entry.getValue() == null ? "" : entry.getValue()).append("&");
        }
        return requstParams.toString().substring(0, requstParams.toString().length() - 1);
    }

}
