/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.impl.commands;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import bot.telegram.crypto.Commands;
import bot.telegram.api.commands.BotCommand;
import bot.telegram.api.commands.CommandRegistry;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author dlevchuk
 */
public abstract class DefaultCommandRegistry<T extends BotCommand> implements CommandRegistry<T> {

    private final Map<Pair<Integer, String>, T> commands = new ConcurrentHashMap<>();

    @Override
    public Collection<T> getRegisteredCommands() {
        return commands.values();
    }

    @Override
    public Map<String, T> getRegisteredCommandsByName() {
//        return commands;
        return null;
    }

    @Override
    public Map<Integer, List<T>> getRegisteredCommandsByState() {
//        Map<Integer, Collection<BotCommand>> ress = new HashMap<>();
//
//        for (Pair<Integer, String> pair : commands.keySet()) {
//            ress.computeIfAbsent(pair.getLeft(), emptyColl -> new ArrayList<>())
//                    .add(commands.get(pair));
//        }
//        return ress;
        return commands.entrySet().stream().
                collect(
                        Collectors.groupingBy(entry -> entry.getKey().getLeft(),
                                Collectors.mapping(entry -> entry.getValue(), Collectors.toList())));
    }

    @Override
    public void register(Integer state, T command) {
        register(new ImmutablePair(state, command.commandName()), command);
    }

    @Override
    public void registerForAllStates(T command) {
        getRegisteredCommandsByState().keySet().stream()
                .forEach(state -> register(state, command));
    }

    @Override
    public void register(Integer state, String name, T command) {
        register(new ImmutablePair<>(state, name), command);
    }

    private void register(Pair<Integer, String> key, T command) {
        commands.put(key, command);
    }

    @Override
    public void register(Pair<Integer, T>... commands) {
        for (Pair<Integer, T> pair : commands) {
            register(pair.getLeft(), pair.getRight());
        }
    }

    @Override
    public T getCommand(Integer state, String commandText) {
        // TODO: refactor change the logic
        if (state == 3) {
            try {
                Integer.parseInt(commandText);
                commandText = Commands.CRYPTO_TIMER;
            } catch (NumberFormatException e) {
                // Skip
            }
        }

        return commands.get(new ImmutablePair(state, commandText));
    }

}
