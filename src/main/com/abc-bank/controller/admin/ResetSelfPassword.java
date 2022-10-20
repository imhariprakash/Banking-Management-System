package controller.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResetSelfPassword extends HttpServlet {
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            HttpSession session = request.getSession();
            if(session.getAttribute("username") == null) {
                response.setStatus(400); // bad request
                response.setContentType("application/json");
                JsonObject json = new JsonObject();
                json.addProperty("status", "400");
                json.addProperty("message", "Please login to change your password!");
            }
            else{
                JsonObject json = new JsonObject();
                String username = (String) session.getAttribute("username");
                JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
                String password = jsonObject.get("password").getAsString();

                dao.admin.ResetPassword.reset(username, password, json);
                if(json.get("status").getAsString().equals("200")) {
                    response.setStatus(200); // ok
                    response.setContentType("application/json");

                    session.removeAttribute("username");
                }else {
                    response.setStatus(400); // bad request
                }
                response.getWriter().println(json);
            }
        }catch(Exception e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            response.setStatus(500);
            json.addProperty("status", "500");
            json.addProperty("message", "Internal server error. " + "You are not logged in!");
            response.setContentType("application/json");
            response.getWriter().println(json);
        }
    }
}
