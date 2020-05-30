/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.idiot.currency;

/**
 * @author dlevchuk
 */
public enum Currency {
    EUR("EUR");

    private String ticket;

    Currency(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return ticket;
    }
}
