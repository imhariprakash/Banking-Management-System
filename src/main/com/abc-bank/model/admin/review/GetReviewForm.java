package model.admin.review;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetReviewForm {
    private GetReviewForm() {
    } // Prevent instantiation

    public static JsonObject getReviewForm(String username){
        JsonObject reviewForm = dao.admin.GetReviewForm.getReviewForm(username);
        return reviewForm;
    }

    public static JsonObject getReviewForm(String username, String email){
        JsonObject reviewForm = dao.admin.GetReviewForm.getReviewForm(username, email);
        return reviewForm;
    }
}
