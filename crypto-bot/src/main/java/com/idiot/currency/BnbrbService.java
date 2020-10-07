/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.idiot.currency;

import java.io.Closeable;
import java.util.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import static org.glassfish.grizzly.utils.Charsets.UTF8_CHARSET;

/**
 *
 * API: http://www.nbrb.by/APIHelp/ExRates
 *
 * {"Cur_ID":292,"Date":"2018-06-18T00:00:00","Cur_Abbreviation":"EUR","Cur_Scale":1,"Cur_Name":"Евро","Cur_OfficialRate":2.3168}
 *
 * @author dlevchuk
 */
public class BnbrbService implements ExchangeRateService {

    private HttpClient httpClient = HttpClients.createDefault();

    private static final String baseUrl = "http://www.nbrb.by/API/ExRates/Rates/%s?ParamMode=2";

    @Override
    public String getRate(Currency currency) {
        String rate = null;
        try {
            final HttpGet request = new HttpGet(String.format(baseUrl, currency.toString()));

//            String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
//            request.setHeader("Authorization", authorizationHeader);
//            String requestJson = wrapperFactory.unwrap(new CreateTaskRequest(taskEntityType, processKey, custometNumber)).toString();

//            request.setHeader("Content-type", "application/json");
//            StringEntity entity = new StringEntity(requestJson);
//            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);

            try {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    throw new IllegalStateException("Can't get currency rate. Status: " + response.getStatusLine());
                }
                String responseJSON = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
                String[] split = responseJSON.split(",");
                for (String s : split) {
                    if (s.contains("Cur_OfficialRate")) {
                        String[] entry = s.split(":");
                        rate = entry[1];
                        break;
                    }
                }
            } finally {
                if (response instanceof Closeable) {
                    ((Closeable)response).close();
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Can't get currency rate", e);
        }



        return rate;
    }
}
