package controller.register;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplicationStatus extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JsonObject jsonObject = new JsonObject();
            String email = request.getParameter("email");

            // check already a customer

            if(dao.register.AlreadyRegistered.isRegistered(email)) {
                response.setStatus(200);
                jsonObject.addProperty("status", "200");
                jsonObject.addProperty("message", "Approved - please login to access your account");
                response.getWriter().println(jsonObject);
                return;
            }

            // check application status in applications table (null means - never registered /  rejected / approved)

            String status = dao.applications.Applications.getApplicationStatus(email);
            if (status == null) {
                status = dao.applications.Applications.getApplicationStatus_from_log(email);
                if(status == null){
                    response.setStatus(200);
                    jsonObject.addProperty("status", "200");
                    jsonObject.addProperty("message", "Application not found, You've never registered! - please register");
                    response.getWriter().println(jsonObject);
                    return;
                }else if(status.equals("approved")){
                    response.setStatus(200);
                    jsonObject.addProperty("status", "200");
                    jsonObject.addProperty("message", "Application is approved - please login");
                    response.getWriter().println(jsonObject);
                    return;
                }
                else if(status.equals("pending")){
                    response.setStatus(200);
                    jsonObject.addProperty("status", "200");
                    jsonObject.addProperty("message", "Application is pending - please wait for approval");
                    response.getWriter().println(jsonObject);
                    return;
                }
                if(status.equals("rejected")) {
                    response.setStatus(200);
                    jsonObject.addProperty("status", "200");
                    jsonObject.addProperty("message", "Application is rejected - please apply again");
                    response.getWriter().println(jsonObject);
                    return;
                }
            } else if(status.equals("pending")) {
                response.setStatus(200);
                jsonObject.addProperty("status", "200");
                jsonObject.addProperty("message", "Application is pending - please wait for approval");
                response.getWriter().println(jsonObject);
                return;
            } else if(status.equals("rejected")){
                response.setStatus(200);
                jsonObject.addProperty("status", "200");
                jsonObject.addProperty("message", "Application is rejected - please apply again");
                response.getWriter().println(jsonObject);
                return;
            }
            else if (status.equals("approved")) {
                response.setStatus(200);
                jsonObject.addProperty("status", "200");
                jsonObject.addProperty("message", "Application is approved - please login");
                response.getWriter().println(jsonObject);
                return;
            }
            response.setStatus(400);
            jsonObject.addProperty("status", "200");
            jsonObject.addProperty("message", "No application found for this email");
            response.getWriter().println(jsonObject);
        } catch (Exception e) {
            System.out.println(e + " in ApplicationStatus");
            JsonObject jsonObject = new JsonObject();
            response.setContentType("application/json");
            jsonObject.addProperty("status", "400");
            jsonObject.addProperty("message", "Bad Request");
        }
    }
}
