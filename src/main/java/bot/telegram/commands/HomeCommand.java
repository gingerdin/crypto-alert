/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands;


import java.util.List;
import java.util.Map;
import bot.telegram.Commands;
import bot.telegram.commands.api.BotCommand;
import bot.telegram.commands.api.CommandRegistry;
import bot.telegram.commands.api.DefaultBotCommand;
import bot.telegram.commands.api.DefaultTransitionBotCommand;
import bot.telegram.commands.api.Transition;
import bot.telegram.commands.crypto.ClearStateCommand;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author dlevchuk
 */
public class HomeCommand extends DefaultTransitionBotCommand {

    private CommandRegistry commandRegistry;

    public HomeCommand(CommandRegistry commandRegistry, Transition transition) {
        super(transition);
        this.commandRegistry = commandRegistry;
    }

    @Override
    public String commandName() {
        return Commands.HOME;
    }

    @Override
    protected String executeAction(Update update) {
        new ClearStateCommand().execute(update);

        StringBuilder helpMessageBuilder = new StringBuilder("<b>Home</b>\n");
        helpMessageBuilder.append("These are the registered commands for this Bot:\n\n");

        for (BotCommand botCommand : ((Map<Integer, List<BotCommand>>)commandRegistry.getRegisteredCommandsByState()).get(1)) {
            helpMessageBuilder.append(botCommand.commandName()).append("\n\n");
        }

        return helpMessageBuilder.toString();
    }
}
