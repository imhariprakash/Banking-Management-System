package model.utilities.transaction_formatter;

public class BuildPage {
    private BuildPage() {
    } // private constructor

    public static String build(String account_number){
        //html
        String page = GetHtml.init();

        // head, bootstrap, jquery, plugin for pagination

        page += GetHtml.getHead();
        page += Pagination.getPaginationPlugin();
        page += Pagination.getBootStrap();
        page += Pagination.getJs();
        page += GetHtml.getEndHead();

        // body

        page += GetHtml.getBody();

        // menu
        page += GetHtml.getMenu();

        // div to center




        return model.utilities.PrettyPrintHtml.prettyPrint(page);
    }
}
