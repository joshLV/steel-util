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
 * @version $Id: BaseRequest.java, v 0.1 2014年12月26日 下午1:31:07 log.yin Exp $
 */
public abstract class BaseRequest implements Serializable, Signaturable {
    /**  */
    private static final long serialVersionUID = 5149581411050809931L;
    private String            version;
    private String            inputCharset;
    private String            signType;
    private String            signMsg;
    private String            clientNo;

    /** 
     * @see com.gw.crm.ivr.rt.dto.Signaturable#getSignatureParmMap()
     */
    @JsonIgnore
    @Override
    public LinkedHashMap<String, String> buildSignatureParmMap() {
        LinkedHashMap<String, String> parmMap = new LinkedHashMap<String, String>();
        parmMap.put("version", version);
        parmMap.put("inputCharset", inputCharset);
        parmMap.put("signType", signType);
        parmMap.put("clientNo", clientNo);
       
        LinkedHashMap<String, String> signatureParamMap = buildBizSignatureParamMap();
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
    protected abstract LinkedHashMap<String, String> buildBizSignatureParamMap();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
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

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }
}
