/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.api.state.machine;

/**
 * @author dlevchuk
 * @since 1.0
 **/

import java.io.Serializable;

public final class State implements Serializable {

    private static final long serialVersionUID = -190117378596283777L;

    private final int state;
    private final String selectedCurrency;
    private final int timer;

    public State(int state, String selectedCurrency, int timer) {
        this.state = state;
        this.selectedCurrency = selectedCurrency;
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public String getSelectedCurrency() {
        return selectedCurrency;
    }

    public int getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state1 = (State) o;

        if (state != state1.state) {
            return false;
        }
        if (timer != state1.timer) {
            return false;
        }
        return selectedCurrency != null ? selectedCurrency.equals(state1.selectedCurrency) : state1.selectedCurrency == null;
    }

    @Override
    public int hashCode() {
        int result = state;
        result = 31 * result + (selectedCurrency != null ? selectedCurrency.hashCode() : 0);
        result = 31 * result + timer;
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "state=" + state +
                ", selectedCurrency='" + selectedCurrency + '\'' +
                ", timer=" + timer +
                '}';
    }
}
