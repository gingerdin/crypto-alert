/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram;

/**
 * @author dlevchuk
 */
public final class Commands {

    public static final String COMMAND_PREFIX = "/";
    public static final String CURRENCY = COMMAND_PREFIX + "currency";
    public static final String HOME = COMMAND_PREFIX + "home";

    public static final String CRYPTO = COMMAND_PREFIX + "crypto";
    public static final String CRYPTO_SHOW_ALL = COMMAND_PREFIX + "crypto_all";
    public static final String CRYPTO_MENU = COMMAND_PREFIX + "crypto_menu";
    public static final String CRYPTO_NEW_ALERT = COMMAND_PREFIX + "crypto_new_alert";
    public static final String CRYPTO_SELECT_CURRENCY = COMMAND_PREFIX + "crypto_select_cur";
    public static final String CRYPTO_TIMER = COMMAND_PREFIX + "crypto_save_timer";
    public static final String CRYPTO_CLEAR_STATE = COMMAND_PREFIX + "crypto_clear_state";

}
