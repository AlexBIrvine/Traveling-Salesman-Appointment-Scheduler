package Model;

import static Utilities.TimeAndPlace.utcToLocal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private int appointmentID;
    private int customerID;
    private int userID;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String URL;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    
    public Appointment (int appointmentID, int customerID, int userID, String title, String description, String location, String contact, String type, String URL, ZonedDateTime start, ZonedDateTime end, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy) {
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.URL = URL;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    public int getAppointmentID() {
        return this.appointmentID;
    }
    
    public void setAppointmentID(int ID) {
        this.appointmentID = ID;
    }
    
    public int getCustomerID() {
        return this.customerID;
    }
    
    public void setCustomerID(int ID) {
        this.customerID = ID;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(int ID) {
        this.userID = ID;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getContact() {
        return this.contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getURL() {
        return this.URL;
    }
    
    public void setURL(String url) {
        this.URL = url;
    }
    
    public ZonedDateTime getStart() {
        return this.start;
    }
    
    public void setStart(ZonedDateTime start) {
        this.start = start;
    }
    
    public ZonedDateTime getEnd() {
        return this.end;
    }
    
    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }
    
    public ZonedDateTime getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(ZonedDateTime date) {
        this.createDate = date;
    }
    
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public ZonedDateTime getLastUpdate() {
        return this.lastUpdate;
    }
    
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    @Override
    public String toString() {
        ZonedDateTime startX = utcToLocal(this.start);
        ZonedDateTime endX = utcToLocal(this.end);
        DateTimeFormatter  dayFormater =  DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter  timeFormater = DateTimeFormatter.ofPattern("hh:mm a");
        String monthAndDay = dayFormater.format(startX);
        String startTime = timeFormater.format(startX);
        String endTime = timeFormater.format(endX);
        String zone = startX.getZone().toString();
        return monthAndDay + "\t\t" + startTime + " - " + endTime + "\t\t" + zone + "\t\t" + this.type;
    }
}