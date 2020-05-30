/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands;

import java.util.List;
import java.util.Map;
import bot.telegram.commands.api.BotCommand;
import bot.telegram.commands.api.Transition;
import bot.telegram.commands.api.TransitionBotCommand;
import bot.telegram.commands.crypto.SaveTimerCommand;
import bot.telegram.commands.crypto.ShowAllCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author dlevchuk
 */
public class DefaultBotRegistryTest {

    @Test
    public void test() {
        TranstionBotCommandRegistry registry = new TranstionBotCommandRegistry();
        registry.register(new ShowAllCommand(new Transition(1,1)));
        registry.register(new HomeCommand(registry, new Transition(1, 1)));
        registry.register(new SaveTimerCommand(null, new Transition(2, 3)));

        Map<Integer, List<TransitionBotCommand>> res = registry.getRegisteredCommandsByState();

        assertEquals(2, res.get(1).size());
        assertEquals(1,  res.get(2).size());
    }

}
