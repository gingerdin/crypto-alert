/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram;

import bot.telegram.api.BotNameProvider;
import bot.telegram.api.state.machine.Transition;
import bot.telegram.crypto.Commands;
import bot.telegram.crypto.CryptoBot;
import bot.telegram.crypto.commands.Currency;
import bot.telegram.crypto.commands.NewAlertCommand;
import bot.telegram.crypto.commands.SaveCurrencyCommand;
import bot.telegram.crypto.commands.ShowAllCommand;
import bot.telegram.impl.commands.HomeCommand;
import bot.telegram.impl.state.machine.TranstionBotCommandRegistry;
import dl.bot.telegram.url.parser.command.UrlParserCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.stream.Stream;

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
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(BotNameProvider botNameProvider, TranstionBotCommandRegistry commandRegistry) {
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new CryptoBot(botNameProvider, commandRegistry));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        return botapi;
    }

    @Bean
    public BotNameProvider botNameProvider() {
        return new bot.telegram.impl.BotNameProvider("iDiotCryptoAlertBot");
    }

    @Bean
    public TranstionBotCommandRegistry cryptoCommandRegistry(BotNameProvider botNameProvider) {
        TranstionBotCommandRegistry commandRegistry = new TranstionBotCommandRegistry();

        /**
         * 1 - Home -> 1
         * 2 - Home -> 1
         * 3 - Home -> 1
         * 1 - NewAlert -> 2
         * 2 - All currencies -> 2
         * 2 - Save Currency -> 3
         * 3 - Save Timer -> 1
         *
         *
         */

        commandRegistry.register(new NewAlertCommand(botNameProvider, new Transition(1, 2)));
        commandRegistry.register(2,  new ShowAllCommand(botNameProvider, new Transition(2, 2)));

        Stream.of(Currency.values())
                .forEach(currency -> commandRegistry.register(Commands.COMMAND_PREFIX + currency.toString(),
                        new SaveCurrencyCommand(botNameProvider, new Transition(2, 3))));

        // Commented during refactoring, as there were no quick possibility to inject the bot to the command
//        commandRegistry.register(new SaveTimerCommand(this, new Transition(3, 1)));
        commandRegistry.registerForAllStates(new HomeCommand(botNameProvider, commandRegistry,
                new Transition(1, 1)));

        commandRegistry.registerForAllStates(new UrlParserCommand(botNameProvider,null,
                new Transition(1, 1)));

        commandRegistry.register();

        return commandRegistry;
    }
}
