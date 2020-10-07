/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.impl;

import java.util.Objects;

/**
 * @author dlevchuk
 * @since 1.0
 **/
public class BotNameProvider implements bot.telegram.api.BotNameProvider {

    private String name;

    public BotNameProvider(String name) {
        this.name = name;
    }

    @Override
    public String getBotName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotNameProvider that = (BotNameProvider) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
