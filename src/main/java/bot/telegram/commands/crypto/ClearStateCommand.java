/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands.crypto;

import bot.telegram.Commands;
import bot.telegram.api.CryptoBot;
import bot.telegram.commands.api.DefaultBotCommand;
import bot.telegram.db.api.DatabaseManager;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

/**
 * @author dlevchuk
 */
public class ClearStateCommand extends DefaultBotCommand {
    @Override
    public String commandName() {
        return Commands.CRYPTO_CLEAR_STATE;
    }

    @Override
    protected String executeAction(Update e) {
        DatabaseManager.getInstance().clearState(CryptoBot.BOT_NAME_HOLDER.get(), e.getMessage().getChat().getUserName(), e.getMessage().getChatId());
        return "";
    }

    @Override
    public SendMessage execute(Update e) {
        return super.execute(e);
    }
}
