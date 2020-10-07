package dl.bot.telegram.url.parser.command;

import bot.telegram.Commands;
import bot.telegram.commands.api.DefaultTransitionBotCommand;
import bot.telegram.commands.api.Transition;
import com.google.gson.JsonObject;
import dl.bot.telegram.url.parser.ContainsUrl;
import dl.bot.telegram.url.parser.api.Url;
import dl.bot.telegram.url.parser.api.UrlParserProvider;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dlevchuk
 * @since 1.0
 **/
public class UrlParserCommand extends DefaultTransitionBotCommand {

    private final UrlParserProvider urlParserProvider;

    public UrlParserCommand(UrlParserProvider urlParserprovider, Transition transition) {
        super(transition);
        this.urlParserProvider = urlParserprovider;
    }

    @Override
    public String commandName() {
        return Commands.URL_PARSER;
    }

    @Override
    protected String executeAction(Update update) {
        Url url = UrlFactory.create(update.getMessage());
//        EntityType entityType = EntityTypeFactory.create(url);
//
//        JsonObject object = UrlParserProvider.get(url).parse(url, entityType);
//
//        Repository repository = RepositoryFactory.get(entityType);
//        repository.create(object);
//
//        UrlParser parser = urlParserProvider.getUrlParser(update.getMessage());

        JsonObject object = new JsonObject();
        object.addProperty("url", url.getName());

        return "The url " + url.getUrl() + " has been parsed to the next object: " + object;
    }

    private static final class UrlFactory {

        private static final Collection<Url> supportedUrl = new ArrayList<>();

        static {
            supportedUrl.add(new ContainsUrl("r.onliner", "Onliner realt"));
            supportedUrl.add(new ContainsUrl("realt.by", "Realt"));
        }


        public static Url create(Message message) {
            if (message == null) {
                throw new IllegalStateException("Message cannot be null");
            }

            String urlStr = message.getText();
            try {
                new URL(urlStr);
            } catch (MalformedURLException e) {
                throw new IllegalStateException("The message is expected to be URL");
            }

            return supportedUrl.stream()
                    .filter(url -> url.supports(urlStr))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Url " + urlStr + " is not supported"));

        }
    }


}
