/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto.commands;

import bot.telegram.api.commands.CommandRegistry;
import bot.telegram.crypto.Commands;
import bot.telegram.impl.commands.DefaultBotCommand;
import org.telegram.telegrambots.meta.api.objects.Update;


/**
 * @author dlevchuk
 */
public class CryptoMenuCommand extends DefaultBotCommand {

    private CommandRegistry commandRegistry;

    public CryptoMenuCommand(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Override
    public String commandName() {
        return Commands.CRYPTO_MENU;
    }

    @Override
    protected String executeAction(Update update) {
        return null;
    }

}
