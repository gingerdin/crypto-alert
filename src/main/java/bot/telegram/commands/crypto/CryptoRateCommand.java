/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.commands.crypto;

import java.util.Timer;
import java.util.TimerTask;
import bot.telegram.Commands;
import bot.telegram.commands.api.DefaultBotCommand;
import bot.telegram.db.api.DatabaseManager;
import bot.telegram.db.api.InMemoryDatabase;
import com.idiot.crypto.CoinmarketcapApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * @author dlevchuk
 */
public class CryptoRateCommand extends DefaultBotCommand {

    private CoinmarketcapApi api = new CoinmarketcapApi();

    private TelegramLongPollingBot sender;

    public CryptoRateCommand(TelegramLongPollingBot sender) {
        this.sender = sender;
    }

    @Override
    public String commandName() {
        return Commands.CRYPTO;
    }

    @Override
    protected String executeAction(Update e) {
        InMemoryDatabase.State state = DatabaseManager.getInstance().getBotState(sender.getBotUsername(), e.getMessage().getChat().getUserName(), e.getMessage().getChatId());

        // (milliseconds in minute) * minutes
        long timerInMls = 60000 * state.getTimer();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (sender == null) {
                    throw new IllegalStateException("Sender cannot be null");
                }
                try {
                    sender.execute(rateMessage(e, state.getSelectedCurrency()));
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }
        }, 10000, timerInMls);

        String message = "Crypto alert configured. " + state.getSelectedCurrency() + " every " + state.getTimer() + " minutes. Step[3/3] Ready!";

        new ClearStateCommand().execute(e);

        return message;
    }

    private SendMessage rateMessage(Update e, String currency) {
        CoinmarketcapApi.CurrencyRate rate = api.rate(currency);

        StringBuilder rateMessageBuilder = new StringBuilder("<b>" + currency.toUpperCase() + "</b>\n");
        rateMessageBuilder.append("USD : " + rate.getPriceUsd());
        rateMessageBuilder.append("\n\n");
        rateMessageBuilder.append("1h : " + rate.getPercentageChange1h());
        rateMessageBuilder.append("\n\n");
        rateMessageBuilder.append("24h : " + rate.getPercentageChange24h());
        rateMessageBuilder.append("\n\n");

        SendMessage rateMessage = new SendMessage();
        rateMessage.setChatId(e.getMessage().getChatId().toString());
        rateMessage.enableHtml(true);
        rateMessage.setText(rateMessageBuilder.toString());

        return rateMessage;
    }

    public void setSender(TelegramLongPollingBot sender) {
        this.sender = sender;
    }



}