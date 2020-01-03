package Model;

import java.time.ZonedDateTime;
import java.util.Calendar;

public class Address {
    
    private int addressID;
    private String address;
    private String address2;
    private int cityID;
    private String postalCode;
    private String phone;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    
    public Address (int addressID, String address, String address2, int cityID, String postalCode, String phone, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lasteUpdatedBy) {
        this.addressID = addressID;
        this.address = address;
        this.address2 = address2;
        this.cityID = cityID;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lasteUpdatedBy;
    }
    
    public int getAddressID() {
        return this.addressID;
    }
    
    public void setAddressID(int ID) {
        this.addressID = ID;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public int getCityID() {
        return this.cityID;
    }
    
    public void setCityID(int ID) {
        this.cityID = ID;
    }
    
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
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
    
    public void setLastUpdate(ZonedDateTime date) {
        this.lastUpdate = date;
    }
    
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
