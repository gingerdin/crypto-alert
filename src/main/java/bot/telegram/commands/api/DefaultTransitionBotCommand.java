/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands.api;

/**
 * @author dlevchuk
 */
public abstract class DefaultTransitionBotCommand extends DefaultBotCommand implements TransitionBotCommand {

    private Transition transition;

    public DefaultTransitionBotCommand(Transition transition) {
        if (transition == null) {

        }
        this.transition = transition;
    }

    @Override
    public Transition getTransition() {
        return transition;
    }
}