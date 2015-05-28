package se.nordichackday.nordictrends;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;

/**
 * Created by anna on 28/05/15.
 */
public class RssSourceApp {

    private static String YLE_FINNISH = "http://yle.fi/uutiset/rss/uutiset.rss";
    private static String YLE_SWEDISH = "http://svenska.yle.fi/nyheter/senaste-nytt.rss";

    public RssSourceApp () {
        SyndFeedInput input = new SyndFeedInput();
        try {
            SyndFeed feed = input.build(new XmlReader(new URL(YLE_SWEDISH)));

            for(Object entry: feed.getEntries()) {
                SyndEntryImpl sf = (SyndEntryImpl) entry;
                System.out.println(sf.getTitle());
            }


        } catch (FeedException e) {
            System.out.println("Något gick fel: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Något gick fel: " + e.getMessage());
        }

    }
}
