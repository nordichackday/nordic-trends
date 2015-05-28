package se.nordichackday.nordictrends;

import java.util.List;

/**
 * Created by anna on 28/05/15.
 */
public class ArticleList {
    private final String source;
    private final List<Article> articles;

    public ArticleList(List<Article> articles, String source) {
        this.articles = articles;
        this.source = source;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public String getSource() {
        return source;
    }


}
