/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands.crypto;

import bot.telegram.Commands;
import bot.telegram.api.CryptoBot;
import bot.telegram.commands.api.DefaultTransitionBotCommand;
import bot.telegram.commands.api.Transition;
import bot.telegram.db.api.DatabaseManager;
import org.telegram.telegrambots.api.objects.Update;

/**
 * @author dlevchuk
 */
public class SaveCurrencyCommand extends DefaultTransitionBotCommand {

    public SaveCurrencyCommand(Transition transition) {
        super(transition);
    }

    @Override
    public String commandName() {
        return Commands.CRYPTO_SELECT_CURRENCY;
    }

    @Override
    protected String executeAction(Update e) {
        String message = e.getMessage().getText();

        DatabaseManager.getInstance().setSelectedCurrency(
                CryptoBot.BOT_NAME_HOLDER.get(),
                e.getMessage().getChat().getUserName(),
                e.getMessage().getChatId(),
                message.substring(1, message.length()));

        return "Great, " + message.substring(1, message.length()) + "\n\nNow please send interval in minutes for the timer [Step 2/3]";
    }
}
