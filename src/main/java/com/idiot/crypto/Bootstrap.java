/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.idiot.crypto;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idiot.crypto.model.kraken.PriceResponse;

/**
 * @author dlevchuk
 */
//@SpringBootApplication
public class Bootstrap {
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }

    // http://localhost:8080/RESTfulExample/json/product/get
    public static void main(String[] args) {

        /*try {

//            URL url = new URL("https://cex.io/api/last_price/BTC/USD");
            URL url = new URL("https://cex.io/api/currency_limits");
//            URL url = new URL("https://api.kraken.com/0/public/Spread?pair=XBTUSD");
            javax.net.ssl.HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuffer buf = new StringBuffer();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                buf.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();
            PriceResponse priceResponse = mapper.readValue(buf.toString(), PriceResponse.class);
            System.out.println(priceResponse);

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }*/

        CexAPI cexApi = new CexAPI("up108917278", "pzM3AYqDiOJYPtEmI5svTTuvVI", "YoJj2BmnZ8JXiQ38EWd5z3P2DQA");

        System.out.print(cexApi.lastPrice("BTC", "USD"));
    }

}
