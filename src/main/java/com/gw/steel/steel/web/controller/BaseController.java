/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2015 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.web.controller;

import java.util.Map;

import com.gw.steel.steel.web.controller.dto.BaseRequest;
import com.gw.steel.steel.web.controller.dto.BaseResponse;

/**
 * 
 * @author log.yin
 * @version $Id: BaseController.java, v 0.1 2015年2月9日 下午2:05:00 log.yin Exp $
 */
public interface BaseController<Req extends BaseRequest, Resp extends BaseResponse> {
    public final static String KEY = "key";

    public Resp wrapper(Req req);

    public boolean handleRequest(Req req, Resp resp, Map<String, String> extendsDataMap);

    public void handleResponse(Req req, Resp resp, Map<String, String> extendsDataMap);
}
