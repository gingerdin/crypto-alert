/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto;

import java.util.List;
import java.util.Objects;

import bot.telegram.api.BotNameProvider;
import bot.telegram.api.CommandAwareBot;
import bot.telegram.api.state.machine.State;
import bot.telegram.impl.state.machine.TranstionBotCommandRegistry;
import bot.telegram.api.commands.CommandRegistry;
import bot.telegram.api.state.machine.TransitionBotCommand;
import bot.telegram.impl.db.DatabaseManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.Nonnull;

/**
 * @author dlevchuk
 */
public class CryptoBot extends TelegramLongPollingBot implements CommandAwareBot {

    private TranstionBotCommandRegistry commandRegistry;
    private BotNameProvider botNameProvider;

    public CryptoBot(@Nonnull BotNameProvider botNameProvider, @Nonnull TranstionBotCommandRegistry commandRegistry) {
        this.botNameProvider = Objects.requireNonNull(botNameProvider);
        this.commandRegistry = Objects.requireNonNull(commandRegistry);
    }

    @Override
    public void onUpdateReceived(Update update) {
        State state = DatabaseManager.getInstance().getBotState(getBotUsername(), update.getMessage().getChat().getUserName(), update.getMessage().getChatId());
        actionOnState(update, state);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        updates.stream()
                .forEach(this::onUpdateReceived);
    }

    @Override
    public String getBotUsername() {
        return botNameProvider.getBotName();
        //возвращаем юзера
    }

    private void actionOnState(Update update, State state) {
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
