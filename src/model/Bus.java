package model;
/**
 * Model class representing a Bus.
 */
public class Bus {
    private int busNumber;
    private String bookingDate;
    private int seatCapacity;
    private int seatsAvailable;
    private String startingPoint;
    private String endingPoint;

    public Bus(int busNumber, int seatCapacity, int seatsAvailable, String startingPoint, String endingPoint, String bookingDate) {

        this.busNumber = busNumber;
        this.bookingDate = bookingDate;
        this.seatCapacity = seatCapacity;
        this.seatsAvailable = seatsAvailable;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

     public int getBusNumber() {
        return busNumber;
    }
    public String getBookingDate() {
        return bookingDate;
    }
    public int getSeatCapacity() {
        return seatCapacity;
    }
    public int getSeatsAvailable() {
        return seatsAvailable;
    }
    public String getStartingPoint() {
        return startingPoint;
    }
    public String getEndingPoint() {
        return endingPoint;
    }
}
