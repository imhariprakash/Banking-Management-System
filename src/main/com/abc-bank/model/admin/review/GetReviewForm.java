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
}
