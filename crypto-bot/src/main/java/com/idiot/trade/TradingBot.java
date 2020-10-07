/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.idiot.trade;

import static com.idiot.trade.Signal.BUY;
import static com.idiot.trade.Signal.NONE;
import static com.idiot.trade.Signal.SELL;

/**
 * https://docs.coinapi.io/?java#latest-data
 *
 * Verium Reserve on bittrex
 * https://www.reddit.com/r/BitcoinMarkets/comments/6k75ue/unable_to_get_historical_bittrex_data/
 *
 *
 * https://www.dropbox.com/s/5mwysbwc5w368gm/Screenshot%202018-07-07%2017.16.22.png?dl=0
 *
 * @author dlevchuk
 */
public class TradingBot {

    private Signal signal = NONE;

    public TradingBot() {
    }

    private void start() {

        Signal signal = null;




        if (signal == BUY) {
            Ticket ticket = sendOrder();
        } else if (signal == SELL) {

        }



    }

    /**
     * Simple moving average
     *
     * https://www.mql5.com/en/docs/indicators/ima
     *
     * @param symbol
     * @param timeframePeriod
     * @param maPeriod
     * @param maShift
     * @param maMethod
     * @param appliedPrice
     * @return
     */
    private double iMa(String symbol, Timeframe timeframePeriod, int maPeriod, int maShift, MaMethod maMethod, AppliedPrice appliedPrice) {

        return 0d;
    }


    private Ticket sendOrder() {
        return null;
    }


    interface Ticket {

    }
}
