/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.api.commands;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import bot.telegram.api.commands.BotCommand;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author dlevchuk
 */
public interface CommandRegistry<T extends BotCommand> {

    Collection<T> getRegisteredCommands();

    Map<String, T> getRegisteredCommandsByName();

    Map<Integer, List<T>> getRegisteredCommandsByState();

    void register(Integer state, T command);

    void registerForAllStates(T command);

    void register(Integer state, String name, T command);

    void register(Pair<Integer, T>... commands);

    T getCommand(Integer state, String commandText);
}
