package com.gw.steel.steel.web.util;

import java.util.LinkedHashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gw.steel.steel.web.controller.dto.BaseRequest;
import com.gw.steel.steel.web.controller.dto.Signaturable;

/**
 *CRM对前端系统接口提供的Md5加密：
 *格式："paramName":"paramVale","paramName":"paramVale",...,"paramName":"paramVale","keyName":"keyVale"
 *对该格式的字符串进行Md5加密，转16进制，并转成大写
 * 
 * @author log.yin
 * @version $Id: MD5SignUtil.java, v 0.1 2015年2月6日 下午1:15:12 log.yin Exp $
 */
public final class MD5SignUtil {

    private static final Log logger = LogFactory.getLog(MD5SignUtil.class);

    public static String buildSignBody(Signaturable signaturable, String secretKey) {
        String signatureBody = buildParamSignatureBody(signaturable.getSignatureParmMap());
        StringBuffer buf = new StringBuffer(signatureBody);
        if (!StringUtils.isBlank(secretKey)) {
            appendSignParameter(buf, "key", secretKey, false);
        }
        return buf.toString();
    }

    public static boolean verifySignature(BaseRequest requestBody, String secretKey) {
        String signatureMsg = getSignatureMsg(requestBody, secretKey);
        return signatureMsg.equals(requestBody.getSignMsg());
    }

    public static String getSignatureMsg(Signaturable requestBody, String secretKey) {
        String sourceSignature = buildSignBody(requestBody, secretKey);
        String sigature = "";
        if (StringUtils.isNotBlank(sourceSignature)) {
            sigature = DigestUtils.md5Hex(sourceSignature.trim()).toUpperCase();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("MD5值: " + sigature + "，MD5签名要素: " + sourceSignature);
        }
        return sigature;
    }

    public static String buildParamSignatureBody(LinkedHashMap<String, String> signatureParamMap) {
        if (signatureParamMap == null) {
            return null;
        }
        StringBuffer sBuffer = new StringBuffer();
        for (String key : signatureParamMap.keySet()) {
            String paramValue = signatureParamMap.get(key);
            appendSignParameter(sBuffer, key, paramValue);
        }
        return sBuffer.toString();
    }

    /**
     * 按键值对把参数key=value增加到buf对象中. 如果为空则不增加.
     * 
     */
    public static void appendSignParameter(final StringBuffer buf, final String key,
                                           final String value) {
        appendSignParameter(buf, key, value, true);
    }

    public static void appendSignParameter(final StringBuffer buf, final String key,
                                           final String value, final boolean appendAndChar) {
        appendSignParameter(buf, key, value, appendAndChar, false);
    }

    /**
     * 按键值对把参数key:value增加到buf对象中. 如果为空则不增加.
     * 
     */
    public static void appendSignParameter(final StringBuffer buf, final String key,
                                           final String value, final boolean appendAndChar,
                                           final boolean containEmptyStr) {
        if (!containEmptyStr && StringUtils.isBlank(value)) {
            return;
        }
        buf.append(key).append(":").append(null == value ? "" : value);
        if (appendAndChar) {
            buf.append(",");
        }
    }
}
