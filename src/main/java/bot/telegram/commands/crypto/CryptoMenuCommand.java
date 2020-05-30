/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands.crypto;

import bot.telegram.Commands;
import bot.telegram.commands.api.CommandRegistry;
import bot.telegram.commands.api.DefaultBotCommand;
import org.telegram.telegrambots.api.objects.Update;

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
    protected String executeAction(Update e) {
        return null;
    }

}
