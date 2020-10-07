package dl.bot.telegram.url.parser.repo.api;

import dl.bot.telegram.url.parser.model.api.Flat;
import dl.bot.telegram.url.parser.model.api.Key;

import java.util.Collection;

/**
 * @author dlevchuk
 * @since 1.0
 **/
public interface FlatRepository {

    Flat create(Flat flat);

    Flat update(Flat flat);

    Flat load(Key key);

    Collection<Flat> loadAll(Collection<Key> keys);
}
