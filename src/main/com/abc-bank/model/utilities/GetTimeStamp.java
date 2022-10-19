package model.utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class GetTimeStamp {
    private GetTimeStamp() {
    } // private constructor

    public static Timestamp getTimeStamp() {
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (Timestamp.valueOf(sdf3.format(new java.util.Date())));
    }
}
