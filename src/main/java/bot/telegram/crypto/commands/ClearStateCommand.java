/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto.commands;

import bot.telegram.crypto.Commands;
import bot.telegram.crypto.CryptoBot;
import bot.telegram.impl.commands.DefaultBotCommand;
import bot.telegram.api.db.DatabaseManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author dlevchuk
 */
public class ClearStateCommand extends DefaultBotCommand {
    @Override
    public String commandName() {
        return Commands.CRYPTO_CLEAR_STATE;
    }

    @Override
    protected String executeAction(Update update) {
        DatabaseManager.getInstance().clearState(CryptoBot.BOT_NAME_HOLDER.get(), update.getMessage().getChat().getUserName(), update.getMessage().getChatId());
        return "";
    }

    @Override
    public SendMessage execute(Update e) {
        return super.execute(e);
    }
}
