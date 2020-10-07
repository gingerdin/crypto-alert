/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto.commands;

import bot.telegram.crypto.Commands;
import bot.telegram.impl.state.machine.DefaultTransitionBotCommand;
import bot.telegram.api.state.machine.Transition;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author dlevchuk
 */
public class NewAlertCommand extends DefaultTransitionBotCommand {

    public NewAlertCommand(Transition transition) {
        super(transition);
    }

    @Override
    public String commandName() {
        return Commands.CRYPTO_NEW_ALERT;
    }

    @Override
    protected String executeAction(Update update) {
        StringBuilder chooseMessage = new StringBuilder("<b>Type currency name(e.g. /tron) [Step 1/3]</b>\n\n");
        chooseMessage.append(Commands.CRYPTO_SHOW_ALL);

        return chooseMessage.toString();
    }
}
