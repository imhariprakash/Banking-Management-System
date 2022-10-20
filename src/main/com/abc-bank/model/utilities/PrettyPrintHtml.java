package model.utilities;

import org.jsoup.Jsoup;

public class PrettyPrintHtml {
    private PrettyPrintHtml() {
    } // private constructor

    public static String prettyPrint(String html) {
        return Jsoup.parse(html).toString();
    }
}
