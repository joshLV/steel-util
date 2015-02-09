/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2015 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.web.constants;

/**
 * 
 * @author log.yin
 * @version $Id: BaseCodeConstants.java, v 0.1 2015年2月9日 下午2:02:03 log.yin Exp $
 */
public class BaseCodeConstants {
    /*
    00000=成功(异步：受理成功，同步：交易成功)

    00001=错误或者未开通的客户端编号
    00002=验签不通过
    00003=接口参数不合法：{0}
                           接口版本号不支持
                           字符编码不支持
                           签名类型不支持
                           
    99999=未知错误或未定义错误
    */

    public final static String SUCCESS          = "00000";

    public final static String INVALID_CLIENTNO = "00001";
    public final static String INVALID_SIGNMSG  = "00002";
    public final static String INVALID_PARAM    = "00003";

    public final static String UNKNOWN_ERROR    = "99999";
}
