package se.nordichackday.nordictrends;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ArticleController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static String YLE_FINNISH = "http://yle.fi/uutiset/rss/uutiset.rss";
    private static String YLE_SWEDISH = "http://svenska.yle.fi/nyheter/senaste-nytt.rss";

    @RequestMapping("/articles")
    public List<ArticleList> greeting(@RequestParam(value="name", defaultValue="World") String name) {

        List<ArticleList> result = new ArrayList<>();

        result.add(getArticleList(YLE_FINNISH));
        result.add(getArticleList(YLE_SWEDISH));

        return result;

    }

    private ArticleList getArticleList(String rssUrl) {

        List<Article> articles = new ArrayList<>();
        try {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(new URL(rssUrl)));

            for(Object entry: feed.getEntries()) {
                SyndEntryImpl sf = (SyndEntryImpl) entry;
                articles.add(new Article(sf.getTitle()));
            }

        } catch (FeedException e) {
            System.out.println("Något gick fel: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Något gick fel: " + e.getMessage());
        }
        return new ArticleList(articles, rssUrl);
    }

}
