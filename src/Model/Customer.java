package Model;

import java.time.ZonedDateTime;

public class Customer {
    
    private int customerID;
    private String customerName;
    private int addressID;
    private int active;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdateBy;
    
    public Customer(int customerID, String customerName, int addressID, int active, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdateBy) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.addressID = addressID;
        this.active = active;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }
    
    public int getCustomerID() {
        return this.customerID;
    }
    
    public void setCustomerID(int ID) {
        this.customerID = ID;
    }
    
    public String getCustomerName() {
        return this.customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public int getAddressID() {
        return this.addressID;
    }
    
    public void setAddressID(int addressID) {
        this.addressID = addressID;
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
    
    public void setLastUpdateBy(String lastUpdatedBy) {
        this.lastUpdateBy = lastUpdatedBy;
    }
    
    @Override
    public String toString() {
        return this.getCustomerName();
    }
}
