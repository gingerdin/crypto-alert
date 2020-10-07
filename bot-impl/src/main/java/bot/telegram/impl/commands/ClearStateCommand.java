/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.impl.commands;


import bot.telegram.api.BotNameProvider;
import bot.telegram.impl.db.DatabaseManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author dlevchuk
 */
public class ClearStateCommand extends DefaultBotCommand {

    String CRYPTO_CLEAR_STATE = "/crypto_clear_state";

    public ClearStateCommand(BotNameProvider botNameProvider) {
        super(botNameProvider);
    }

    @Override
    public String commandName() {
        return CRYPTO_CLEAR_STATE;
    }

    @Override
    protected String executeAction(Update update) {
        DatabaseManager.getInstance().clearState(getBotNameProvider().getBotName(), update.getMessage().getChat().getUserName(), update.getMessage().getChatId());
        return "";
    }

    @Override
    public SendMessage execute(Update e) {
        return super.execute(e);
    }
}
