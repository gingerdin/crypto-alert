/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.idiot.crypto;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import static org.glassfish.grizzly.utils.Charsets.UTF8_CHARSET;

/**
 * @author dlevchuk
 */
public class CoinmarketcapApi {

    private HttpClient httpClient = HttpClients.createDefault();

    private static final String baseUrl = "https://api.coinmarketcap.com/v1/ticker/%s";


    public static void main(String[] arg) {
        CoinmarketcapApi cnm = new CoinmarketcapApi();
        CurrencyRate rate = cnm.rate("tron");

        System.out.println(rate.getPriceUsd());
        System.out.println(rate.getPriceBtc());
        System.out.println(rate.getPercentageChange1h());
        System.out.println(rate.getPercentageChange24h());
        System.out.println(rate.getPercentageChange7d());
    }


    public CurrencyRate rate(String coin) {
        try {
            final HttpGet request = new HttpGet(String.format(baseUrl, coin));

            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new IllegalStateException("Can't get currency rate. Status: " + response.getStatusLine());
            }
            String responseJSON = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(responseJSON).getAsJsonArray().get(0).getAsJsonObject();

            return map(mainObject);




        } catch (Exception ex) {
            throw new IllegalStateException("Can't get currency rate", ex);
        }
    }

    private CurrencyRate map(JsonObject object) {
        CurrencyRate rate = new CurrencyRate();
        rate.setPriceUsd(object.get("price_usd").getAsString());
        rate.setPriceBtc(object.get("price_btc").getAsString());
        rate.setPercentageChange1h(object.get("percent_change_1h").getAsString());
        rate.setPercentageChange24h(object.get("percent_change_24h").getAsString());
        rate.setPercentageChange7d(object.get("percent_change_7d").getAsString());

        return rate;
    }

    public static class CurrencyRate {
        private String priceUsd;
        private String priceBtc;
        private String percentageChange1h;
        private String percentageChange24h;
        private String percentageChange7d;


        public String getPriceUsd() {
            return priceUsd;
        }

        public void setPriceUsd(String priceUsd) {
            this.priceUsd = priceUsd;
        }

        public String getPriceBtc() {
            return priceBtc;
        }

        public void setPriceBtc(String priceBtc) {
            this.priceBtc = priceBtc;
        }

        public String getPercentageChange1h() {
            return percentageChange1h;
        }

        public void setPercentageChange1h(String percentageChange1h) {
            this.percentageChange1h = percentageChange1h;
        }

        public String getPercentageChange24h() {
            return percentageChange24h;
        }

        public void setPercentageChange24h(String percentageChange24h) {
            this.percentageChange24h = percentageChange24h;
        }

        public String getPercentageChange7d() {
            return percentageChange7d;
        }

        public void setPercentageChange7d(String percentageChange7d) {
            this.percentageChange7d = percentageChange7d;
        }
    }
}
