package model.accounts;

import com.google.gson.JsonObject;
import model.validation.*;

import java.sql.Date;

public class CreateAccount {
    private CreateAccount(){
    } // private constructor to prevent instantiation

    public static final String STATUS = "status";
    public static final String MESSAGE = "message";

    public static void create(JsonObject request, JsonObject response){
        String cust_name, father_name, email, phone, aadhar, pan, address;
        int pincode;
        Date dob;

        // validate name

        try{
            cust_name = ValidateApplication.capitalize(request.get("name").getAsString().trim());
            if(!NameValidation.validate(cust_name)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid name");
                return;
            }
            request.addProperty("name", cust_name);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid name");
            return;
        }

        // validate father name

        try {
            father_name = ValidateApplication.capitalize(request.get("f_name").getAsString().trim());
            if(!NameValidation.validate(father_name)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid father name");
                return;
            }
            request.addProperty("f_name", father_name);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid father's name");
            return;
        }

        // validate email

        try {
            email = request.get("email").getAsString().trim().toLowerCase();
            if(!EmailValidation.isValidEmail(email)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid email");
                return;
            }
            request.addProperty("email", email);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid email");
            return;
        }

        // validate phone

        try {
            phone = request.get("phone").getAsString().trim().replace("\\s", "").replace("+91", "");
            phone = phone.replace("-", "").replace("(", "").replace(")", "");
            if(!MobileNumberValidation.isValidMobileNumber(phone)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid phone number");
                return;
            }
            request.addProperty("phone", phone);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid phone number");
            return;
        }

        // validate aadhar

        try{
            aadhar = request.get("aadhar").getAsString().trim().replace("\\s", "").replace(" ", "");
            if(!AadharValidation.isValidAadhar(aadhar)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid aadhar number");
                return;
            }
            request.addProperty("aadhar", aadhar);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid aadhar number");
            return;
        }

        // validate pan

        try {
            pan = request.get("pan").getAsString().trim().replace("\\s", "").replace(" ", "").toUpperCase();
            if(!PanValidation.validate(pan)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid pan number");
                return;
            }
            request.addProperty("pan", pan);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid pan number");
            return;
        }

        // validate address

        try{
            address = request.get("address").getAsString().trim();
            if(!AddressValidation.validate(address)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid address");
                return;
            }
            request.addProperty("address", address);
        }catch (Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid address");
            return;
        }

        // validate pin code

        try{
            pincode = request.get("pincode").getAsInt();
            if(!PincodeValidation.isValidPincode(pincode)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid pincode");
                return;
            }
            request.addProperty("pincode", pincode);
        }catch (Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid pincode");
            return;
        }

        // validate dob

        try{
            dob = Date.valueOf(ValidateApplication.getDate(request.get("dob").getAsString().trim()).toString());
            if(!AgeValidation.validate(dob)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Age should be greater than 18");
                return;
            }
            request.addProperty("dob", dob.toString());
        }catch (Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid date of birth");
            return;
        }

        dao.accounts.CreateAccount.create(request, response);
        String applied_on = dao.applications.Applications.getAppliedOn(email);
        dao.applications.Applications.updateApplication_log(email, applied_on, "approved", request.get("verified_by").getAsString());
        dao.applications.Applications.deleteApplication(email);

        if(response.get(STATUS).getAsString().equals("500")){
            response.addProperty(MESSAGE, "Internal server error");
            return;
        }
        response.addProperty(STATUS, "200");
        response.addProperty(MESSAGE, "Account created successfully");
        Mail.mail(email);
    }
}
