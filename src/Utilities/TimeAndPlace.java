package Utilities;

import static Utilities.CustomExceptions.fyiMessage;
import DAO.AppointmentDAO;
import Model.Appointment;
import Model.User;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javafx.collections.ObservableList;

public class TimeAndPlace {
    
    //sets locale to system default
    private static Locale locale = Locale.getDefault();
    
    //Returns country name
    public static String getCountry() {
        return locale.getDisplayCountry();
    }
    
    //returns current local
    public static Locale getLocale() {
        return locale;
    }
    
    
    //Sets local to Germany (TESTING PURPOSES ONLY)
    public static void setToGerman() {
        locale = Locale.GERMANY;
    }
    
    
    //Checks to see if Appoinment starts within 15 minutes, alerts user if true
    public static void alertIfAppointmentSoon(User user) throws Exception {
        
        ObservableList<Appointment> appointments = AppointmentDAO.getAllAppointments();
        ZonedDateTime now = ZonedDateTime.now();
        int userID = user.getUserID();
        
        //Checks each appointment's start time to be within 15 minutes of now
        for (Appointment apt : appointments) {
            if (apt.getUserID() == userID && 
                    ChronoUnit.MINUTES.between(now, apt.getStart()) < 15 && 
                    ChronoUnit.MINUTES.between(now, apt.getStart()) > 0) {
                        fyiMessage("You have an appointment within 15 minutes from now!");
                        break;
            }
        }
    }
    
    
    //Converts UTC to Local time
    public static ZonedDateTime utcToLocal(ZonedDateTime utc) {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        return utc.withZoneSameInstant(localZoneId);
    }
    
    
    //Converts Local time to UTC
    public static ZonedDateTime localToUTC(ZonedDateTime local) {
        return local.withZoneSameInstant(ZoneOffset.UTC);
    }
    
    
    //Convert TimeStamp to String
    public static String timestampToString(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(timestamp);
    }
    
    
    //Convert ZonedDateTime to String
    public static String zonedDateTimeToString (ZonedDateTime zdt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zdt.format(formatter);
    }
    
    //Convert String to ZonedDateTime
    public static ZonedDateTime stringToZonedDateTime (String str) {
        return ZonedDateTime.parse(str);
    }
    
}
