package dl.bot.telegram.url.parser;

import dl.bot.telegram.url.parser.api.Url;

/**
 * @author dlevchuk
 * @since 1.0
 **/
public class ContainsUrl implements Url {

    private final String urlPart;
    private final String name;

    public ContainsUrl(String urlPart, String name) {
        this.urlPart = urlPart;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return urlPart;
    }

    @Override
    public boolean supports(String url) {
        if (url == null) {
            throw new IllegalStateException("The url cannot be null");
        }
        return url.contains(urlPart);
    }
}
