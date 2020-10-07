/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package bot.telegram.impl.db;

import bot.telegram.api.db.Database;
import bot.telegram.impl.db.InMemoryDatabase;

/**
 * TODO: Should be refactored to bw a registry, that automatically register the
 * need database
 *
 * @author dlevchuk
 */
public final class DatabaseManager {

    private static ThreadLocal<Database> dbHolder = ThreadLocal.withInitial(() -> new InMemoryDatabase());

    public static Database getInstance() {
        return dbHolder.get();
    }
}
