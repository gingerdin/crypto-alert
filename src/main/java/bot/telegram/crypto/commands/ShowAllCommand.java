/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto.commands;

import java.util.Arrays;

import bot.telegram.crypto.Commands;
import bot.telegram.impl.state.machine.DefaultTransitionBotCommand;
import bot.telegram.api.state.machine.Transition;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author dlevchuk
 */
public class ShowAllCommand extends DefaultTransitionBotCommand {

    public ShowAllCommand(Transition transition) {
        super(transition);
    }

    @Override
    public String commandName() {
        return Commands.CRYPTO_SHOW_ALL;
    }

    @Override
    protected String executeAction(Update update) {
        StringBuilder all = new StringBuilder("<b>All crypto currencies</b>\n\n");
        Arrays.stream(Currency.values())
                .forEach(currency -> all.append(Commands.COMMAND_PREFIX + currency.toString() + " "));

        return all.toString();
    }
}
