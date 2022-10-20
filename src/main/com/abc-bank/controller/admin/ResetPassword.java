package controller.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResetPassword extends HttpServlet {
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null || (!session.getAttribute("username").equals("admin"))){
            response.setStatus(401);
            JsonObject json = new JsonObject();
            json.addProperty("status", "401");
            json.addProperty("message", "Must be logged in as a super admin to reset password");
            response.setContentType("application/json");
            response.getWriter().println(json);// Handle an invalid JWT
            return;
        }

        try {
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            JsonObject json = new JsonObject();
            model.admin.ResetPassword.reset(jsonObject, json);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
