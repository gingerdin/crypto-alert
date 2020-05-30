/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.db.api;

/**
 * @author dlevchuk
 */
public interface Database {

    InMemoryDatabase.State getBotState(String botUserName, String userName, Long chatId);

    void setBotState(String botUserName, String userName, Long chatId, int state);

    void setSelectedCurrency(String botUserName, String userName, Long chatId, String selectedCurrency);

    void setTimer(String botUserName, String userName, Long chatId, int timer);

    void clearState(String botUserName, String userName, Long chatId);
}
