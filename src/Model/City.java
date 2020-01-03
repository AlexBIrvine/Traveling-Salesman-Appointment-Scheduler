package Model;

import java.time.ZonedDateTime;



public class City {
    
    private int cityID;
    private String city;
    private int countryID;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    
    public City(int cityID, String city, int countryID, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy) {
        this.cityID = cityID;
        this.city = city;
        this.countryID = countryID;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    public int getCityID() {
        return this.cityID;
    }
    
    public void setCityID(int cityID) {
        this.cityID = cityID;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public int getCountryID() {
        return this.countryID;
    }
    
    public void setCountryID(int ID) {
        this.countryID = ID;
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
    
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}