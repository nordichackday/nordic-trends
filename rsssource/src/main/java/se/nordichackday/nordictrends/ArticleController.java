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

@RestController
public class ArticleController {

    public enum Company{
        SVT ("SVT", "http://www.svt.se/nyheter/rss.xml"),
        YLE_FINNISH ("Yle (fi)", "http://yle.fi/uutiset/rss/uutiset.rss"),
        YLE_SWEDISH ("Yle (sv)", "http://svenska.yle.fi/nyheter/senaste-nytt.rss"),
        NRK ("NRK","http://www.nrk.no/nyheter/siste.rss"),
        RUV ("RUV", "http://www.ruv.is/rss/innlent"),
        DR ("DR", "http://www.dr.dk/nyheder/service/feeds/allenyheder");

        public final String name;
        public final String url;

        Company(String name, String url) {
            this.name = name;
            this.url = url;
        }
    }

    @RequestMapping("/articles")
    public List<ArticleList> greeting(@RequestParam(value="addArticleText", defaultValue = "false") boolean withArticleText) {

        List<ArticleList> result = new ArrayList<>();

        result.add(getArticleList(Company.SVT, withArticleText));
        result.add(getArticleList(Company.YLE_FINNISH, withArticleText));
        result.add(getArticleList(Company.YLE_SWEDISH, withArticleText));
        result.add(getArticleList(Company.NRK, withArticleText));
        result.add(getArticleList(Company.RUV, withArticleText));
        result.add(getArticleList(Company.DR, withArticleText));

        return result;

    }

    private ArticleList getArticleList(Company company, boolean withArticleText) {

        List<Article> articles = new ArrayList<>();
        try {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(new URL(company.url)));

            for(Object entry: feed.getEntries()) {
                SyndEntryImpl sf = (SyndEntryImpl) entry;
                articles.add(cleanArticle(sf, withArticleText));
            }

        } catch (FeedException e) {
            System.out.println("Något gick fel: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Något gick fel: " + e.getMessage());
        }
        return new ArticleList(articles, company);
    }

    private Article cleanArticle(SyndEntryImpl sf, boolean withArticleText) {
        String cleanDescription = TextCleaner.clean(sf.getDescription().getValue());
        Article article = new Article(sf.getTitle(), cleanDescription, sf.getLink());
        return article;
    }

}
