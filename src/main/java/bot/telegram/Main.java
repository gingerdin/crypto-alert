/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram;

import bot.telegram.api.CryptoBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Timer executor
 * https://github.com/rubenlagus/TelegramBotsExample/blob/master/src/main/java/org/telegram/services/TimerExecutor.java
 *
 * https://github.com/rubenlagus/TelegramBots/blob/master/TelegramBots.wiki/FAQ.md
 * https://github.com/rubenlagus/TelegramBotsExample/blob/master/src/main/java/org/telegram/updateshandlers/WeatherHandlers.java
 *
 * Historic price data http://alt19.com/
 *
 *
 *
 * TODO:
 * + 1) Implement command with parameters (crypto->tron->time)
 * +  1.1) Make State immutable (change concurrently)
 *    1.2) All currencies by calling the coinmarketcap
 *   2) Implement commands without response
 *   3) Call commands from commands
 *   4) Save db before exit the program (In case of InMemoryDB)
 *   5) Change architecture to handle not only direct commands (e.g. /home) but any text
 *
 * @author dlevchuk
 */
public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new CryptoBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
