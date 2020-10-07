/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.api.state.machine;

import bot.telegram.api.commands.BotCommand;

/**
 * Bot command that supports {@link Transition}
 *
 * @author dlevchuk
 */
public interface TransitionBotCommand extends BotCommand {

    Transition getTransition();
}