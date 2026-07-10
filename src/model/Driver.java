package model;
/**
 * Model class representing a Driver.
 */
public class Driver {
    private String driverName;
    private int busNumber;
    private int driverId;
    
    public Driver(String driverName, int busNumber, int driverId) {
        this.busNumber = busNumber;
        this.driverName = driverName;
        this.driverId = driverId;
    }

    public int getBusNumber() {
        return busNumber;
    }
    public String getDriverName() {
        return driverName;
    }
    public int getDriverId() {
        return driverId;
    }

}
