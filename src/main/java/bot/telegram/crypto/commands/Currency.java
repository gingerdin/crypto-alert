/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto.commands;

/**
 * @author dlevchuk
 */
public enum Currency {
    TRON("tron"),
    VERIUM_RESERVE("veriumreserve"),
    RIPPLE("ripple"),
    DRAGONCHAIN("dragonchain"),
    STELLAR("stellar"),
    ELECTRONEUM("electroneum"),
    BITCOIN("bitcoin"),
    ETHEREUM("ethereum");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
