package com.shn.gmall.bean;

import java.io.Serializable;

/**
 * @author sss
 */
public class SkuLsAttrValue implements Serializable {
    private static final long serialVersionUID = 5941332810273469721L;
    private String valueId;

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }
}
