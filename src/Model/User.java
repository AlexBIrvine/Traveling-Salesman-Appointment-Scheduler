package Model;

import java.time.ZonedDateTime;

public class User {

    private int userID;
    private String userName;
    private String password;
    private int active;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdateBy;
    
    public User(int userID, String userName, String password, int active, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdateBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(int ID) {
        this.userID = ID;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getActive() {
        return this.active;
    }
    
    public void setActive(int active) {
        this.active = active;
    }
    
    public ZonedDateTime getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
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
    
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }
    
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}