package se.nordichackday.nordictrends;

import com.jaunt.*;

/**
 * Created by anna on 29/05/15.
 */
public class ArticleScraper {

    public static String getCleanArticle(String url, String description) throws ResponseException, NotFound {
        Document doc = getDocument(url);
        return clean(doc.innerText(), description);

        /*Element element = doc.findFirst(description);
        return element.innerHTML();*/

    }

    public static String clean(String articleText, String description) {

        int i = articleText.indexOf("> "+description);
        i = i < 0 ? articleText.indexOf(">"+description) : i;
        System.out.println("Text på plats nr: "+i +"-> "+description);

        if(i>0) {
            return articleText.substring(i, i+description.length());
        }

        return articleText;
        //return clean(articleText);

    }

    private static Document getDocument(String url) throws ResponseException {
        UserAgent userAgent = new UserAgent();
        userAgent.visit(url);
        return userAgent.doc;
    }
}
