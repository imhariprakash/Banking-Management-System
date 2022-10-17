package controller.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RejectApplication extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
        JsonObject json = new JsonObject();
        model.accounts.RejectApplication.reject(jsonObject, json);
        response.setContentType("application/json");
        if(json.get("status").getAsString().equals("500")) {
            response.setStatus(500);
            response.getWriter().println(json);
        }
        else if(json.get("status").getAsString().equals("200")) {
            response.setStatus(200);
            response.getWriter().println(json);
        }
        else {
            response.setStatus(400);
            response.getWriter().println(json);
        }
    }
}
