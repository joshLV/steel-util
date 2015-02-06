/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2015 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util.common;

import java.util.Map;

/**
 * 
 * @author log.yin
 * @version $Id: Md5ParamsGenerator.java, v 0.1 2015年2月3日 下午7:25:16 log.yin Exp $
 */
public class Md5ParamsGenerator {
    /**
     * 
     * key1value1key2value2key3value3...
     * @param map
     * @return
     */
    public static String generateMd5Params(Map<String, String> map) {
        StringBuilder signString = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            signString.append(entry.getKey()).append(entry.getValue());
        }
        return signString.toString();
    }

    /**
     * key1=value1&key2=value2&key3=value3...
     * 
     * @param map
     * @param keyLable
     * @return
     */
    public static String generateHttpGetParams(Map<String, String> map, String keyLable) {
        StringBuilder requstParams = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (keyLable != null && !keyLable.equals(entry.getKey())) {
                requstParams.append(entry.getKey()).append("=").append(entry.getValue())
                    .append("&");
            }
        }
        return requstParams.toString().substring(0, requstParams.toString().length() - 1);
    }

}
