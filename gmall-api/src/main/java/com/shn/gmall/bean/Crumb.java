package com.shn.gmall.bean;

import java.io.Serializable;

/**
 * @author sss
 */
public class Crumb implements Serializable {

    private static final long serialVersionUID = 4645637094906095577L;

    private String valueName;

    private String urlParam;

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getUrlParam() {
        return urlParam;
    }

    public void setUrlParam(String urlParam) {
        this.urlParam = urlParam;
    }
}
