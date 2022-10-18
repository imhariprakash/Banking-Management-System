package controller.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");
        try {
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            JsonObject json = new JsonObject();
            model.admin.login.Login.login(jsonObject, json);
            response.setContentType("application/json");
            if (json.get("status").getAsString().equals("500")) {
                response.setStatus(500);
                response.getWriter().println(json);
            } else if (json.get("status").getAsString().equals("200")) {
                response.setStatus(200);
                response.getWriter().println(json);
            } else {
                response.setStatus(400);
                response.getWriter().println(json);
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            JsonObject json = new JsonObject();
            json.addProperty("status", "500");
            json.addProperty("message", "Internal Server Error");
            response.getWriter().println(json);
        }
    }
}
