package controller.atm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Block extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account_number = dao.utility.GetAccountNumber.getAccountNumber(session.getAttribute("customer_id").toString());


        dao.utility.BlockCard.blockCard(account_number);

        response.sendRedirect("http://localhost:8080/abc-bank/");
    }
}
