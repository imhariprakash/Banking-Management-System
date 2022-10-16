package model.validation;

public class NotesValidation {
    private NotesValidation() {
    } // Prevents instantiation

    public static boolean validate(String notes){
        if(notes.length() > 250){
            return false;
        }
        return true;
    }
}
