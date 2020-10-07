/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.api.state.machine;

import java.util.Objects;

/**
 * Transition that transit the from the current state to the next state
 *
 * @author dlevchuk
 */
public final class Transition {
    private final int currentState;
    private final int nextState;

    public Transition(int currentState, int nextState) {
        this.currentState = currentState;
        this.nextState = nextState;
    }

    public int getNextState() {
        return nextState;
    }

    public int getCurrentState() {
        return currentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return currentState == that.currentState &&
                nextState == that.nextState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState, nextState);
    }
}
