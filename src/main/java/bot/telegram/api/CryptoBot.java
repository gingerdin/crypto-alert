/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.api;

import java.util.Arrays;
import java.util.List;

import bot.telegram.Commands;
import bot.telegram.commands.HomeCommand;
import bot.telegram.commands.TranstionBotCommandRegistry;
import bot.telegram.commands.api.CommandRegistry;
import bot.telegram.commands.api.Transition;
import bot.telegram.commands.api.TransitionBotCommand;
import bot.telegram.commands.crypto.Currency;
import bot.telegram.commands.crypto.NewAlertCommand;
import bot.telegram.commands.crypto.SaveCurrencyCommand;
import bot.telegram.commands.crypto.SaveTimerCommand;
import bot.telegram.commands.crypto.ShowAllCommand;
import bot.telegram.db.api.DatabaseManager;
import bot.telegram.db.api.InMemoryDatabase;
import dl.bot.telegram.url.parser.command.UrlParserCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author dlevchuk
 */
public class CryptoBot extends TelegramLongPollingBot implements CommandAwareBot {
    private TranstionBotCommandRegistry commandRegistry;


    public static final ThreadLocal<String> BOT_NAME_HOLDER = new InheritableThreadLocal<>();

    public CryptoBot() {
        this.commandRegistry = new TranstionBotCommandRegistry();

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

        this.commandRegistry.register(new NewAlertCommand(new Transition(1, 2)));
        this.commandRegistry.register(2,  new ShowAllCommand(new Transition(2, 2)));

        Arrays.stream(Currency.values()).forEach(currency -> this.commandRegistry.register(Commands.COMMAND_PREFIX + currency.toString(), new SaveCurrencyCommand(new Transition(2, 3))));

        this.commandRegistry.register(new SaveTimerCommand(this, new Transition(3, 1)));
        this.commandRegistry.registerForAllStates(new HomeCommand(commandRegistry, new Transition(1, 1)));



        this.commandRegistry.registerForAllStates(new UrlParserCommand(null, new Transition(1, 1)));

        this.commandRegistry.register();
    }

    @Override
    public void onUpdateReceived(Update update) {
        BOT_NAME_HOLDER.set(getBotUsername());
        InMemoryDatabase.State state = DatabaseManager.getInstance().getBotState(getBotUsername(), update.getMessage().getChat().getUserName(), update.getMessage().getChatId());
        actionOnState(update, state);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        updates.stream()
                .forEach(this::onUpdateReceived);
    }

    @Override
    public String getBotUsername() {
        return "iDiotCryptoAlertBot";
        //возвращаем юзера
    }



    private void actionOnState(Update update, InMemoryDatabase.State state) {
        String commandText = update.getMessage().getText();

        TransitionBotCommand command = commandRegistry.getCommand(state.getState(), commandText);
        if (command != null) {
            SendMessage message = command.execute(update);
            if (message != null) {
                try {
                    execute(message);
                    DatabaseManager.getInstance().setBotState(getBotUsername(), update.getMessage().getChat().getUserName(), update.getMessage().getChatId(), command.getTransition().getNextState());
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return "542578616:AAG1NMEvuW-dCH9h_jnPu_3vgY9Okbh0IOM";
        //Токен бота
    }

    @Override
    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }
}
