package se.nordichackday.nordictrends;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {
    public String title;
    public String description;
    public String url;
    public String articleText;

    public Article(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

}
