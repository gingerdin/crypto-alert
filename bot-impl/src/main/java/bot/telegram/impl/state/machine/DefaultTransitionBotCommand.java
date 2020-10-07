/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.impl.state.machine;

import bot.telegram.api.BotNameProvider;
import bot.telegram.api.state.machine.Transition;
import bot.telegram.api.state.machine.TransitionBotCommand;
import bot.telegram.impl.commands.DefaultBotCommand;

/**
 * @author dlevchuk
 */
public abstract class DefaultTransitionBotCommand extends DefaultBotCommand implements TransitionBotCommand {

    private Transition transition;

    public DefaultTransitionBotCommand(BotNameProvider botNameProvider, Transition transition) {
        super(botNameProvider);
        if (transition == null) {

        }
        this.transition = transition;
    }

    @Override
    public Transition getTransition() {
        return transition;
    }
}