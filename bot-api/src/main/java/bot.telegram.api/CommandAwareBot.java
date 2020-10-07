/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.api;

import bot.telegram.api.commands.CommandRegistry;

/**
 * Interface that each bot that contains command registry should follow
 *
 * @author dlevchuk
 */
public interface CommandAwareBot {

    CommandRegistry getCommandRegistry();
}
