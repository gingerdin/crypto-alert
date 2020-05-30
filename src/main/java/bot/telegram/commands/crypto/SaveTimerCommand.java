/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands.crypto;

import bot.telegram.Commands;
import bot.telegram.api.CryptoBot;
import bot.telegram.commands.api.DefaultTransitionBotCommand;
import bot.telegram.commands.api.Transition;
import bot.telegram.db.api.DatabaseManager;
import bot.telegram.db.api.InMemoryDatabase;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

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
    protected String executeAction(Update e) {
        int timer;
        try {
            timer = Integer.parseInt(e.getMessage().getText());

        } catch (NumberFormatException ex) {
            return "Wrong timer format";
        }

        DatabaseManager.getInstance().setTimer(CryptoBot.BOT_NAME_HOLDER.get(), e.getMessage().getChat().getUserName(), e.getMessage().getChatId(), timer);

        InMemoryDatabase.State state = DatabaseManager.getInstance().getBotState(CryptoBot.BOT_NAME_HOLDER.get(), e.getMessage().getChat().getUserName(), e.getMessage().getChatId());

        return "For " + state.getSelectedCurrency() + ", timer is " + timer;
    }

    @Override
    public SendMessage execute(Update e) {
        executeAction(e);
        CryptoRateCommand rateCommand = new CryptoRateCommand(bot);
        return rateCommand.execute(e);
    }
}
