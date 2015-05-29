package se.nordichackday.nordictrends;

/**
 * Created by anna on 29/05/15.
 */
public class TextCleaner {

    public static String clean(String text) {

        return text.replaceAll("\\<[^>]*\\>", "").trim();
    }

}
