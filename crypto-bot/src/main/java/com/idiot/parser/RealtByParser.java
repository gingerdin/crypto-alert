/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.idiot.parser;

import java.util.List;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

/**
 * @author dlevchuk
 */
public class RealtByParser {


    public static void main(String[] args) {
        RealtByParser parser = new RealtByParser();
        parser.parse(CityOptions.MINSK, "Мястровская", "24", RoomOptions.THREE);

    }

    public enum CityOptions {
        MINSK("5102");

        private String value;

        CityOptions(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public String getValue() {
            return value;
        }
    }

    public enum RoomOptions {
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5");

        private String value;

        RoomOptions(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public String getValue() {
            return value;
        }
    }



    public void parse(CityOptions city, String street, String flat, RoomOptions rooms) {

        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("https://realt.by/sale/flats/search/#tabs");
            System.out.println(page.getTitleText());
            HtmlSelect citySelect = page.getElementByName("tx_uedbflat_pi2[DATA][town_id][e]");
            HtmlOption minsk = citySelect.getOptionByValue(city.getValue());
            citySelect.setSelectedAttribute(minsk, true);

            System.out.println(minsk.asText());

            HtmlInput streetInput = page.getElementByName("tx_uedbflat_pi2[DATA][street_name][like][0]");
            streetInput.setValueAttribute(street);

            System.out.println(streetInput.asText());


            HtmlInput flatInput = page.getElementByName("tx_uedbflat_pi2[DATA][house_number][range][0]");
            flatInput.setValueAttribute(flat);

            System.out.println(flatInput.asText());

            HtmlSelect roomsSelect = page.getElementByName("tx_uedbflat_pi2[DATA][rooms][e][1]");
            HtmlOption roomOption = roomsSelect.getOptionByValue(rooms.getValue());
            roomsSelect.setSelectedAttribute(roomOption, true);




//            final HtmlForm searchForm = page.getFormByName("tx_uedbflat_pi2");


            DomElement button = page.getElementById("search-list");
            HtmlPage searchReuslt = button.click();
            webClient.waitForBackgroundJavaScript(1000);

            System.out.println(searchReuslt.getTitleText());

            List<DomElement> res = searchReuslt.getByXPath( "//p[@class='search-info-counts']");
            System.out.println(res.get(0).asText());

        } catch (Exception ex) {

        }


    }




}
