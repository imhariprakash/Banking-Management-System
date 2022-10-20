package model.utilities.transaction_formatter;

import dao.pojo.Transaction;

import java.util.ArrayList;

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
        page += GetHtml.getCss();
        page += GetHtml.getEndHead();

        // body

        page += GetHtml.getBody();

        // menu
        page += GetHtml.getMenu();

        // br

        page += GetHtml.getBr();
        page += GetHtml.getBr();

        // div to center
        page += GetHtml.getDiv();

        // table
        page += TableFormatter.init();

        // header
        page += TableFormatter.getHeader();

        // start body
        page += TableFormatter.startBody();

        // get rows
        ArrayList<Transaction> transactions = dao.utility.GetTransactions.getTransactions(account_number);

        for (Transaction transaction : transactions) {
            page += TableFormatter.getRow(transaction.getT_time(), transaction.getFrom_account_number(), transaction.getTo_account_number(), transaction.getTransaction_source(), transaction.getTransaction_type(), transaction.getDescription(), transaction.getAmount());
        }

        // end table body
        page += TableFormatter.endBody();

        // end table
        page += TableFormatter.end();

        // get pagination
        page += Pagination.getPagination();

        // end div
        page += GetHtml.getEndDiv();

        // back button at center
        page += GetHtml.getBackButton();

        // end body
        page += GetHtml.getEndBody();

        // end html
        page += GetHtml.end();



        return model.utilities.PrettyPrintHtml.prettyPrint(page);
    }
}
