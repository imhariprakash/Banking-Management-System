package controller.atm;

import javax.servlet.http.HttpSession;

public class UnBlock extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        String account_number = dao.utility.GetAccountNumber.getAccountNumber(session.getAttribute("customer_id").toString());


        dao.utility.UnBlockCard.unBlockCard(account_number);

        response.sendRedirect("http://localhost:8080/abc-bank/");
    }
}

