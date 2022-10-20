package controller.transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TransactionsLog extends HttpServlet {
    private TransactionsLog() {
    } // private constructor

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        if(session.getAttribute("customer_id") == null) {
            response.sendRedirect("/login");
        }
        String account_number = dao.utility.GetAccountNumber.getAccountNumber(session.getAttribute("customer_id").toString());
        try{
            String page = model.utilities.transaction_formatter.BuildPage.build(account_number);
            response.getWriter().println(page);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
