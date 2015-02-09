/**
 * 
 *大智慧股份有限公司
 * Copyright (c) 2006-2014 DZH,Inc.All Rights Reserved.
 */
package com.gw.steel.steel.web.controller.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author log.yin
 * @version $Id: BaseResponse.java, v 0.1 2014年12月26日 下午1:31:16 log.yin Exp $
 */
public abstract class BaseResponse implements Serializable, Signaturable {
    /**  */
    private static final long serialVersionUID = 8684981170385284479L;
    private String            code;
    private String            message;
    private String            signType;
    private String            signMsg;

    @JsonIgnore
    @Override
    public LinkedHashMap<String, String> getSignatureParmMap() {
        LinkedHashMap<String, String> parmMap = new LinkedHashMap<String, String>();
        parmMap.put("signType", signType);

        LinkedHashMap<String, String> signatureParamMap = getBizSignatureParamMap();
        if (signatureParamMap != null) {
            parmMap.putAll(signatureParamMap);
        }
        return parmMap;
    }

    /**
     * 获取用于加签的业务参数列表
     * 
     * @return
     */
    protected abstract LinkedHashMap<String, String> getBizSignatureParamMap();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

}
