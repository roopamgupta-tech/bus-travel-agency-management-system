package model;
/**
 * Model class representing a Customer.
 */

public class Customer {
    private int passengerId;
    private String name;
    private int age;
    private int busNumber;
    private String bookingDate;
    private double amount;
    private String startingPoint;
    private String endingPoint;

    public Customer(int passengerId, String name, int age, int busNumber, String bookingDate, double amount, String startingPoint, String endingPoint) {
        this.age = age;
        this.amount = amount;
        this.bookingDate = bookingDate;
        this.busNumber = busNumber;
        this.endingPoint = endingPoint;
        this.startingPoint = startingPoint;
        this.passengerId = passengerId;
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public double getAmount() {
        return amount;
    }
    public String getBookingDate() {
        return bookingDate;
    }
    public int getBusNumber() {
        return busNumber;
    }
    public String getEndingPoint() {
        return endingPoint;
    }
    public String getName() {
        return name;
    }
    public int getPassengerId() {
        return passengerId;
    }
    public String getStartingPoint() {
        return startingPoint;
    }
    
}
