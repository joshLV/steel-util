/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2015 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.web.controller;

import java.text.MessageFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.gw.steel.steel.util.common.CodeResourcesUtil;
import com.gw.steel.steel.web.constants.BaseCodeConstants;
import com.gw.steel.steel.web.controller.dto.BaseRequest;
import com.gw.steel.steel.web.controller.dto.BaseResponse;
import com.gw.steel.steel.web.util.MD5SignUtil;

/**
 * 
 * @author log.yin
 * @version $Id: GenericController.java, v 0.1 2015年2月9日 上午10:24:46 log.yin Exp $
 */
public abstract class GenericController<Req extends BaseRequest, Resp extends BaseResponse>
                                                                                            implements
                                                                                            BaseController<Req, Resp> {
    protected abstract Resp execute(Req req);

    protected abstract Resp handleCommonRequestParams(Req req);

    public abstract Map<String, String> getExtendsDataMap(Req req);

    @Override
    public Resp wrapper(Req req) {
        //first call handleInvalidRequest method, to get subclass of resp, it's convienent to set resp value
        Resp resp = handleCommonRequestParams(req);
        Map<String, String> extendsDataMap = getExtendsDataMap(req);
        //check req
        if (handleRequest(req, resp, extendsDataMap)) {
            resp = execute(req);
        }
        //response
        handleResponse(req, resp, extendsDataMap);

        return resp;
    }

    @Override
    public boolean handleRequest(Req req, Resp resp, Map<String, String> extendsDataMap) {
        String secretKey = extendsDataMap.get(KEY);
        //check clientNo & secretKey together
        if (StringUtils.isBlank(secretKey)) {
            resp.setCode(BaseCodeConstants.INVALID_CLIENTNO);
            return false;
        }
        if (!MD5SignUtil.verifySignature(req, secretKey)) {
            resp.setCode(BaseCodeConstants.INVALID_SIGNMSG);
            return false;
        }

        if (StringUtils.isBlank(req.getVersion()) || !"1.0".equals(req.getVersion().trim())) {
            resp.setCode(BaseCodeConstants.INVALID_PARAM);
            resp.setMessage(MessageFormat.format(
                CodeResourcesUtil.getProperty(BaseCodeConstants.INVALID_PARAM), "接口版本号不支持"));
            return false;
        }

        if (StringUtils.isBlank(req.getInputCharset()) || !"1".equals(req.getInputCharset().trim())) {
            resp.setCode(BaseCodeConstants.INVALID_PARAM);
            resp.setMessage(MessageFormat.format(
                CodeResourcesUtil.getProperty(BaseCodeConstants.INVALID_PARAM), "字符编码不支持"));
            return false;
        }

        if (StringUtils.isBlank(req.getSignType()) || !"1".equals(req.getSignType().trim())) {
            resp.setCode(BaseCodeConstants.INVALID_PARAM);
            resp.setMessage(MessageFormat.format(
                CodeResourcesUtil.getProperty(BaseCodeConstants.INVALID_PARAM), "签名类型不支持"));
            return false;
        }

        return true;
    }

    @Override
    public void handleResponse(Req req, Resp resp, Map<String, String> extendsDataMap) {
        String secretKey = extendsDataMap.get(KEY);
        if (StringUtils.isBlank(resp.getCode())) {
            resp.setCode(BaseCodeConstants.SUCCESS);
        }
        if (!BaseCodeConstants.INVALID_PARAM.equals(resp.getCode())) {
            resp.setMessage(CodeResourcesUtil.getProperty(resp.getCode()));
        }

        resp.setSignType(req.getSignType());
        String signMsg = MD5SignUtil.getSignatureMsg(resp, secretKey);
        resp.setSignMsg(signMsg);
    }
}
