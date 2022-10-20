package model.utilities.transaction_formatter;

public class TableFormatter {
    private TableFormatter() {
    } // private constructor

    public static String init(){
        return("<table class=\"table table-bordered table-hover\">");
    }

    public static String getHeader(){
        return("<thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Date</th>\n" +
                "      <th scope=\"col\">From</th>\n" +
                "      <th scope=\"col\">To</th>\n" +
                "      <th scope=\"col\">Source</th>\n" +
                "      <th scope=\"col\">Type</th>\n" +
                "      <th scope=\"col\">Description</th>\n" +
                "      <th scope=\"col\">Amount</th>\n" +
                "    </tr>\n" +
                "</thead>");
    }



    public static String startBody(){
        return("<tbody>");
    }

    public static String endBody(){
        return("</tbody>");
    }

    public static String end(){
        return("</table>");
    }

    public static String getRow(String date, String from, String to, String source, String type, String description, String amount){
        return("<tr><td>" + date + "</td><td>" + from + "</td><td>" + to + "</td><td>" + source + "</td><td>" + type + "</td><td>" + description + "</td><td>" + amount + "</td></tr>");
    }
}
