package se.nordichackday.nordictrends;

import java.util.List;

/**
 * Created by anna on 28/05/15.
 */
public class ArticleList {
    private final String source;
    private final String company;
    private final List<Article> articles;

    public ArticleList(List<Article> articles, ArticleController.Company company) {
        this.articles = articles;
        this.company = company.name;
        this.source = company.url;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public String getSource() {
        return source;
    }


    public String getCompany() {
        return company;
    }
}
