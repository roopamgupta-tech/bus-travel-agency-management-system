package dao;

// DAO class for Bus database operations

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Bus;
import util.DBConnection;
/**
 * BusDao class handles all database operations
 * related to the Bus table.
 */
public class BusDao {

    // Adds a new bus after performing all validations
    public void addBus(Bus bus) {

        // Validate input fields
        if (bus.getStartingPoint().trim().isEmpty()) {
            System.out.println("Starting Point cannot be empty.");
            return;
        }

        if (bus.getEndingPoint().trim().isEmpty()) {
            System.out.println("Ending Point cannot be empty.");
            return;
        }

        if (!bus.getBookingDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid Date Format. Use YYYY-MM-DD");
            return;
        }

        if(bus.getStartingPoint()
        .equalsIgnoreCase(bus.getEndingPoint())){
            System.out.println("Source and Destination can't be same.");
            return;
        }

        if (bus.getSeatCapacity() <= 0) {
            System.out.println("Seat Capacity must be greater than 0.");
            return;
        }

        if (bus.getSeatsAvailable() < 0 ||
            bus.getSeatsAvailable() > bus.getSeatCapacity()) {
            System.out.println("Invalid Available Seats.");
            return;
        }
        // Check if bus number already exists
        if (searchBus(bus.getBusNumber()) != null) {
            System.out.println("Bus Number Already Exists.");
            return;
        }

        // Insert bus record into database
        String query = "INSERT INTO Bus VALUES(?,?,?,?,?,?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement prepareStatement = connection.prepareStatement(query);

            prepareStatement.setInt(1, bus.getBusNumber());
            prepareStatement.setString(2, bus.getBookingDate());
            prepareStatement.setInt(3, bus.getSeatCapacity());
            prepareStatement.setInt(4, bus.getSeatsAvailable());
            prepareStatement.setString(5, bus.getStartingPoint());
            prepareStatement.setString(6, bus.getEndingPoint());
            
            // Execute insert query
            int rows = prepareStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Bus Added Successfully.");
            } else {
                System.out.println("Bus Not Added.");
            }

            prepareStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Displays all buses from the database
    public void showAllBuses() {
        String query = "SELECT * FROM bus";
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement prepareStatement = connection.prepareStatement(query);
            ResultSet resultSet = prepareStatement.executeQuery();
            System.out.println();
            while (resultSet.next()) {
                System.out.println("Bus Number      : "+resultSet.getInt("bus_number"));
                System.out.println("Booking Date    : "+resultSet.getString("booking_date"));
                System.out.println("Seat Capacity   : "+resultSet.getInt("seat_capacity"));
                System.out.println("Available Seats : "+resultSet.getInt("seats_available"));
                System.out.println("Starting Point  : "+resultSet.getString("starting_point"));
                System.out.println("Ending Point    : "+resultSet.getString("ending_point"));
                System.out.println("=======================================");
            }

            resultSet.close();
            prepareStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Searches a bus using its bus number
    public Bus searchBus(int busNumber) {

        String query = "SELECT * FROM bus WHERE bus_number = ?";

        try {

            Connection connection = DBConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, busNumber);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {

                Bus bus = new Bus(
                        resultSet.getInt("bus_number"),
                        resultSet.getInt("seat_capacity"),
                        resultSet.getInt("seats_available"),
                        resultSet.getString("starting_point"),
                        resultSet.getString("ending_point"),
                        resultSet.getString("booking_date")
                );

                resultSet.close();
                prepareStatement.close();
                connection.close();

                return bus;

            } else {

                resultSet.close();
                prepareStatement.close();
                connection.close();

                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Updates available seats of a bus
    public void updateSeats(int busNumber, int seatsAvailable) {
        Bus bus = searchBus(busNumber);

        if (bus == null) {
            System.out.println("Bus Not Found.");
            return;
        }

        if (seatsAvailable < 0 || seatsAvailable > bus.getSeatCapacity()) {
            System.out.println("Invalid Available Seats.");
            return;
        }
        String query = "UPDATE bus SET seats_available = ? WHERE bus_number = ?";
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, seatsAvailable);
            prepareStatement.setInt(2, busNumber);
            int rows = prepareStatement.executeUpdate();

            if(rows > 0){
                System.out.println("Seats Updated Successfully.");
            }
            else{
                System.out.println("Bus Not Found.");
            }

            prepareStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Deletes a bus from the database
    public void deleteBus(int busNumber) {

        Bus bus = searchBus(busNumber);

        if (bus == null) {
            System.out.println("Bus Not Found.");
            return;
        }
        String query = "DELETE FROM bus WHERE bus_number = ?";
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, busNumber);
            int rows = prepareStatement.executeUpdate();

            if(rows > 0){
                System.out.println("Bus Deleted Successfully.");
            }
            else{
                System.out.println("Bus Not Found.");
            }
            
            prepareStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
}