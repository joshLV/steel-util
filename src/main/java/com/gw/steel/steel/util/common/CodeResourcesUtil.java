/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.util.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author log.yin
 * @version $Id: ResourcesConstantsUtil.java, v 0.1 2014年12月29日 下午1:10:59 log.yin Exp $
 */
public class CodeResourcesUtil {
    private static final Log    logger     = LogFactory.getLog(CodeResourcesUtil.class);

    private static Properties   properties = new Properties();

    private final static String FILE_PATH  = "code.properties";

    static {
        InputStream inputStream = null;
        try {
            inputStream = CodeResourcesUtil.class.getClassLoader().getResourceAsStream(FILE_PATH);
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }

    private CodeResourcesUtil() {

    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
