package model.validation;

import com.google.gson.JsonObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ValidateApplication {

    public static final String STATUS = "status";
    public static final String MESSAGE = "message";


    private ValidateApplication() {
    } // Prevents instantiation

    public static void validate(JsonObject json, JsonObject response){

        // magic variables



        // Initialize variables
        String name, f_name, email, phone, aadhar, pan, address, notes;
        int pincode;
        Date dob;

        // validate name

        try{
            name = capitalize(json.get("name").getAsString().trim());
            if(!NameValidation.validate(name)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid name");
                return;
            }
            json.addProperty("name", name);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid name");
            return;
        }

        // validate father name

        try {
            f_name = capitalize(json.get("f_name").getAsString().trim());
            if(!NameValidation.validate(f_name)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid father name");
                return;
            }
            json.addProperty("f_name", f_name);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid father's name");
            return;
        }

        // validate email

        try {
            email = json.get("email").getAsString().trim().toLowerCase();
            if(!EmailValidation.isValidEmail(email)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid email");
                return;
            }
            json.addProperty("email", email);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid email");
            return;
        }

        // validate phone

        try {
            phone = json.get("phone").getAsString().trim().replace("\\s", "").replace("+91", "");
            phone = phone.replace("-", "").replace("(", "").replace(")", "");
            if(!MobileNumberValidation.isValidMobileNumber(phone)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid phone number");
                return;
            }
            json.addProperty("phone", phone);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid phone number");
            return;
        }

        // validate aadhar

        try{
            aadhar = json.get("aadhar").getAsString().trim().replace("\\s", "").replace(" ", "");
            if(!AadharValidation.isValidAadhar(aadhar)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid aadhar number");
                return;
            }
            json.addProperty("aadhar", aadhar);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid aadhar number");
            return;
        }

        // validate pan

        try {
            pan = json.get("pan").getAsString().trim().replace("\\s", "").replace(" ", "").toUpperCase();
            if(!PanValidation.validate(pan)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid pan number");
                return;
            }
            json.addProperty("pan", pan);
        }catch(Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid pan number");
            return;
        }

        // validate address

        try{
            address = json.get("address").getAsString().trim();
            if(!AddressValidation.validate(address)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid address");
                return;
            }
            json.addProperty("address", address);
        }catch (Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid address");
            return;
        }

        // validate pin code

        try{
            pincode = json.get("pincode").getAsInt();
            if(!PincodeValidation.isValidPincode(pincode)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Invalid pincode");
                return;
            }
            json.addProperty("pincode", pincode);
        }catch (Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid pincode");
            return;
        }

        // validate dob

        try{
            dob = Date.valueOf(getDate(json.get("dob").getAsString().trim()).toString());
            if(!AgeValidation.validate(dob)){
                response.addProperty(STATUS, "400");
                response.addProperty(MESSAGE, "Age should be greater than 18");
                return;
            }
            json.addProperty("dob", dob.toString());
        }catch (Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid date of birth");
            return;
        }

        // validate notes

        try{
            notes = json.get("notes").getAsString().trim();
            if(notes.length() == 0){
                notes = "No notes";
            }
            else if(!NotesValidation.validate(notes)){
                notes = notes.substring(0, 250);
            }
            json.addProperty("notes", notes);
        }catch (Exception e){
            response.addProperty(STATUS, "400");
            response.addProperty(MESSAGE, "Invalid notes");
            return;
        }

        if(dao.register.AlreadyRegistered.isAlreadyRegistered(email, phone, pan, aadhar, response)){
            return; // already registered
        }

        // check if user already exists / user data is already present in database(aadhar, pan, phone, email)


        // ready to update database and send email


        dao.register.Register.register(json, response);
        Mail.mail(email);

    }

    public static String capitalize(String str){
        str = str.trim().replace("\\s", " ");
        String[] words = str.split(" ");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }

    public static Date getDate(String date){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(dateFormat.parse(date).getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


/*

            model.validation.ValidateApplication.validate(json, response);
            String name = capitalize(json.get("name").getAsString().trim().toLowerCase());
            String f_name = capitalize(json.get("f_name").getAsString().trim());
            String email = json.get("email").getAsString().trim().toLowerCase();
            String phone = json.get("phone").getAsString().trim().replace("\\s", "");
            String aadhar = json.get("aadhar").getAsString().trim().replace("\\s", "").replace(" ", "");
            String pan = json.get("pan").getAsString().trim().replace("\\s", "").toUpperCase();
            String address = json.get("address").getAsString().trim();
            int pincode = json.get("pincode").getAsInt();
            Date dob = getDate(json.get("dob").getAsString());
            String notes = json.get("notes").getAsString().trim();

 */