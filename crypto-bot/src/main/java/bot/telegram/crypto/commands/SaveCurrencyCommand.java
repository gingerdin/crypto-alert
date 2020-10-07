/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto.commands;

import bot.telegram.api.BotNameProvider;
import bot.telegram.api.state.machine.Transition;
import bot.telegram.crypto.Commands;
import bot.telegram.impl.state.machine.DefaultTransitionBotCommand;
import bot.telegram.impl.db.DatabaseManager;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author dlevchuk
 */
public class SaveCurrencyCommand extends DefaultTransitionBotCommand {

    public SaveCurrencyCommand(BotNameProvider botNameProvider, Transition transition) {
        super(botNameProvider, transition);
    }

    @Override
    public String commandName() {
        return Commands.CRYPTO_SELECT_CURRENCY;
    }

    @Override
    protected String executeAction(Update update) {
        String message = update.getMessage().getText();

        DatabaseManager.getInstance().setSelectedCurrency(
                getBotNameProvider().getBotName(),
                update.getMessage().getChat().getUserName(),
                update.getMessage().getChatId(),
                message.substring(1, message.length()));

        return "Great, " + message.substring(1, message.length()) + "\n\nNow please send interval in minutes for the timer [Step 2/3]";
    }
}
