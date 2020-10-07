/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.crypto;

/**
 * @author dlevchuk
 */
public interface Commands {

    String COMMAND_PREFIX = "/";
    String CURRENCY = COMMAND_PREFIX + "currency";
    String HOME = COMMAND_PREFIX + "home";

    String CRYPTO = COMMAND_PREFIX + "crypto";
    String CRYPTO_SHOW_ALL = COMMAND_PREFIX + "crypto_all";
    String CRYPTO_MENU = COMMAND_PREFIX + "crypto_menu";
    String CRYPTO_NEW_ALERT = COMMAND_PREFIX + "crypto_new_alert";
    String CRYPTO_SELECT_CURRENCY = COMMAND_PREFIX + "crypto_select_cur";
    String CRYPTO_TIMER = COMMAND_PREFIX + "crypto_save_timer";
    String CRYPTO_CLEAR_STATE = COMMAND_PREFIX + "crypto_clear_state";

    // Url Parser
    String URL_PARSER = "url_parser";


}
