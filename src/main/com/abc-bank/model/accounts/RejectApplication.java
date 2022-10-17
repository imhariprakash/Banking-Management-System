package model.accounts;

import com.google.gson.JsonObject;

public class RejectApplication {
    private RejectApplication() {
    }// private constructor

    public static void reject(JsonObject request, JsonObject response){
        try{
            String email = request.get("email").getAsString();
            String name = request.get("name").getAsString();
            String review = request.get("review").getAsString();
            String applied_on = dao.applications.Applications.getAppliedOn(email);
            if(applied_on != null){
                Mail.rejectApplication(email, name, review, applied_on);
            }

            if(model.validation.EmailValidation.isValidEmail(email)){
                Mail.rejectApplication(email, name, review, null);
            }

            dao.applications.Applications.deleteApplication(email);
            dao.applications.Applications.updateApplication_log(email, applied_on, "rejected", request.get("verified_by").getAsString());
        }catch(Exception e){
            System.out.println(e + " in RejectApplication for model");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }
}
