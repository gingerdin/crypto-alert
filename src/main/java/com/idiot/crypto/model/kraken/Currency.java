/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.idiot.crypto.model.kraken;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author dlevchuk
 */
@JsonAutoDetect
public class Currency {
    private XBTResult xxbtzusd;

    public XBTResult getXxbtzusd() {
        return xxbtzusd;
    }

    public void setXxbtzusd(XBTResult xxbtzusd) {
        this.xxbtzusd = xxbtzusd;
    }
}
