/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands;

import bot.telegram.commands.api.TransitionBotCommand;

/**
 * @author dlevchuk
 */
public class TranstionBotCommandRegistry extends DefaultCommandRegistry<TransitionBotCommand> {

    public void register(TransitionBotCommand botCommand) {
        super.register(botCommand.getTransition().getCurrentState(), botCommand);
    }

    public void register(String name, TransitionBotCommand botCommand) {
        super.register(botCommand.getTransition().getCurrentState(), name, botCommand);
    }

}
