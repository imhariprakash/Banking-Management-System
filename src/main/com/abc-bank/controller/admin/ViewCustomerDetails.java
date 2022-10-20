package controller.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewCustomerDetails extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account_number = request.getParameter("account_number");
        response.getWriter().println(model.utilities.customer_details_formatter.Build.build(account_number));
    }
}
