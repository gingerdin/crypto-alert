/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.impl.commands;

import bot.telegram.api.BotNameProvider;
import bot.telegram.api.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author dlevchuk
 */
public abstract class DefaultBotCommand implements BotCommand {

    private BotNameProvider botNameProvider;

    public DefaultBotCommand(BotNameProvider botNameProvider) {
        this.botNameProvider = botNameProvider;
    }

    protected abstract String executeAction(Update update);

    @Override
    public SendMessage execute(Update e) {
        String text = executeAction(e);

        SendMessage reply = new SendMessage();
        reply.setChatId(e.getMessage().getChatId().toString());
        reply.enableHtml(true);
        reply.setText(text);

        return reply;
    }

    public BotNameProvider getBotNameProvider() {
        return botNameProvider;
    }
}
