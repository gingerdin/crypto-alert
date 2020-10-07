/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto.commands;

import bot.telegram.crypto.Commands;
import bot.telegram.crypto.CryptoBot;
import bot.telegram.impl.state.machine.DefaultTransitionBotCommand;
import bot.telegram.api.state.machine.Transition;
import bot.telegram.api.db.DatabaseManager;
import bot.telegram.impl.db.InMemoryDatabase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author dlevchuk
 */
public class SaveTimerCommand extends DefaultTransitionBotCommand {

    private TelegramLongPollingBot bot;

    public SaveTimerCommand(TelegramLongPollingBot bot, Transition transition) {
        super(transition);
        this.bot = bot;
    }

    @Override
    public String commandName() {
        return Commands.CRYPTO_TIMER;
    }

    @Override
    protected String executeAction(Update update) {
        int timer;
        try {
            timer = Integer.parseInt(update.getMessage().getText());

        } catch (NumberFormatException ex) {
            return "Wrong timer format";
        }

        DatabaseManager.getInstance().setTimer(CryptoBot.BOT_NAME_HOLDER.get(), update.getMessage().getChat().getUserName(), update.getMessage().getChatId(), timer);

        InMemoryDatabase.State state = DatabaseManager.getInstance().getBotState(CryptoBot.BOT_NAME_HOLDER.get(), update.getMessage().getChat().getUserName(), update.getMessage().getChatId());

        return "For " + state.getSelectedCurrency() + ", timer is " + timer;
    }

    @Override
    public SendMessage execute(Update e) {
        executeAction(e);
        CryptoRateCommand rateCommand = new CryptoRateCommand(bot);
        return rateCommand.execute(e);
    }
}
