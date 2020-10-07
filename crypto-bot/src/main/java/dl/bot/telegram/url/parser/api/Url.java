package dl.bot.telegram.url.parser.api;

/**
 * @author dlevchuk
 * @since 1.0
 **/
public interface Url {
    String getName();
    String getUrl();

    boolean supports(String url);
}
