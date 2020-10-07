/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.impl.db;

import bot.telegram.api.db.Database;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dlevchuk
 */
public class InMemoryDatabase implements Database {

    private Map<StateKey, State> states = new ConcurrentHashMap<>();

    @Override
    public State getBotState(String botUserName, String userName, Long chatId) {
        return states.computeIfAbsent(new StateKey(botUserName, userName, chatId), empty -> new State(1, null, 0));
    }

    @Override
    public void setBotState(String botUserName, String userName, Long chatId, int state) {
        // init bot state in case it was not initialised before
        getBotState(botUserName, userName, chatId);

        states.computeIfPresent(
                new StateKey(botUserName, userName, chatId),
                (stateKey, existingState) -> new State(state, existingState.getSelectedCurrency(), existingState.getTimer()));
    }

    @Override
    public void setSelectedCurrency(String botUserName, String userName, Long chatId, String selectedCurrency) {
        states.computeIfPresent(
                new StateKey(botUserName, userName, chatId),
                (stateKey, existingState) -> new State(existingState.getState(), selectedCurrency, existingState.getTimer()));
    }

    @Override
    public void setTimer(String botUserName, String userName, Long chatId, int timer) {
        states.computeIfPresent(
                new StateKey(botUserName, userName, chatId),
                (stateKey, existingState) -> new State(existingState.getState(), existingState.getSelectedCurrency(), timer));
    }

    @Override
    public void clearState(String botUserName, String userName, Long chatId) {
        states.remove(new StateKey(botUserName, userName, chatId));
    }

    public final static class State implements Serializable {

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

    private static final class StateKey implements Serializable {

        private static final long serialVersionUID = -2094458404748341761L;

        private final String botUserName;
        private final String userName;
        private final Long chatId;

        public StateKey(String botUserName, String userName, Long chatId) {
            this.botUserName = botUserName;
            this.userName = userName;
            this.chatId = chatId;
        }

        public String getBotUserName() {
            return botUserName;
        }

        public String getUserName() {
            return userName;
        }

        public Long getChatId() {
            return chatId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            StateKey stateKey = (StateKey) o;

            if (botUserName != null ? !botUserName.equals(stateKey.botUserName) : stateKey.botUserName != null) {
                return false;
            }
            if (userName != null ? !userName.equals(stateKey.userName) : stateKey.userName != null) {
                return false;
            }
            return chatId != null ? chatId.equals(stateKey.chatId) : stateKey.chatId == null;
        }

        @Override
        public int hashCode() {
            int result = botUserName != null ? botUserName.hashCode() : 0;
            result = 31 * result + (userName != null ? userName.hashCode() : 0);
            result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "StateKey{" +
                    "botUserName='" + botUserName + '\'' +
                    ", userName='" + userName + '\'' +
                    ", chatId=" + chatId +
                    '}';
        }
    }
}
