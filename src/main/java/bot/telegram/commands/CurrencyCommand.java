/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands;

import java.util.Timer;
import java.util.TimerTask;
import bot.telegram.Commands;
import bot.telegram.commands.api.DefaultBotCommand;
import com.idiot.currency.BnbrbService;
import com.idiot.currency.Currency;
import com.idiot.currency.ExchangeRateService;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * @author dlevchuk
 */
public class CurrencyCommand extends DefaultBotCommand {

    private final ExchangeRateService exchangeRateService = new BnbrbService();

    private AbsSender sender;

    public CurrencyCommand(AbsSender sender) {
        this.sender = sender;
    }

    public CurrencyCommand() {

    }

    @Override
    public String commandName() {
        return Commands.CURRENCY;
    }

    @Override
    protected String executeAction(Update e) {
        return null;
    }

    @Override
    public SendMessage execute(Update e) {
        startCurrencyAlerts(e.getMessage().getChatId());
        return new SendMessage(e.getMessage().getChatId(), "Currency alert configured");
    }

    private void startCurrencyAlerts(Long chatId) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (sender == null) {
                    throw new IllegalStateException("Sender cannot be null");
                }
                SendMessage message = new SendMessage(chatId, exchangeRateService.getRate(Currency.EUR));

                try {
                    sender.execute(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }
        }, 10000, 10000);

    }

    public void setSender(AbsSender sender) {
        this.sender = sender;
    }
}
