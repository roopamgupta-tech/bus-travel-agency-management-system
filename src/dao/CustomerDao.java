package dao;

// DAO class for Customer database operations

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Bus;
import model.Customer;
import util.DBConnection;
/**
 * CustomerDao class manages ticket booking,
 * searching, displaying and cancellation.
 */
public class CustomerDao {

    BusDao busDAO = new BusDao();

    /**
     * Books a ticket after validating passenger details.
     */
    public void bookTicket(Customer customer) {

        if (customer.getName().trim().isEmpty()) {
            System.out.println("Passenger Name cannot be empty.");
            return;
        }
        
        if (customer.getStartingPoint().trim().isEmpty()) {
            System.out.println("Starting Point cannot be empty.");
            return;
        }

        if (customer.getEndingPoint().trim().isEmpty()) {
            System.out.println("Ending Point cannot be empty.");
            return;
        }


        if (searchCustomer(customer.getPassengerId()) != null) {
            System.out.println("Passenger ID Already Exists.");
            return;
        }
        Bus bus = busDAO.searchBus(customer.getBusNumber());


        if (bus == null) {
            System.out.println("Bus Not Found.");
            return;
        }

        // Check Seats
        if (bus.getSeatsAvailable() <= 0) {
            System.out.println("Sorry! Bus is Full.");
            return;
        }

        if (customer.getAge() < 1 || customer.getAge() > 120) {
            System.out.println("Invalid Age.");
            return;
        }
        
        if (customer.getAmount() <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        if(customer.getStartingPoint()
        .equalsIgnoreCase(customer.getEndingPoint())){
            System.out.println("Source and Destination can't be same.");
            return;
        }

        if (!customer.getBookingDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid Date Format.");
            return;
        }

        String query = "INSERT INTO Customer VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            Connection connection = DBConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);

            prepareStatement.setInt(1, customer.getPassengerId());
            prepareStatement.setString(2, customer.getName());
            prepareStatement.setInt(3, customer.getAge());
            prepareStatement.setInt(4, customer.getBusNumber());
            prepareStatement.setString(5, customer.getBookingDate());
            prepareStatement.setDouble(6, customer.getAmount());
            prepareStatement.setString(7, customer.getStartingPoint());
            prepareStatement.setString(8, customer.getEndingPoint());

            int rows = prepareStatement.executeUpdate();
            if (rows > 0) {
                // Seats Update
                busDAO.updateSeats(bus.getBusNumber(), bus.getSeatsAvailable() - 1);
                System.out.println("Ticket Booked Successfully.");
            } else {
                System.out.println("Booking Failed.");
            }

            prepareStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays all booked passengers.
     */

    public void showAllCustomers() {
                String query = "SELECT * FROM customer";
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement prepareStatement = connection.prepareStatement(query);
            ResultSet resultSet = prepareStatement.executeQuery();
            System.out.println();
            while (resultSet.next()) {
                System.out.println("Passenger ID    : "+resultSet.getInt("passenger_id"));
                System.out.println("Passenger Name  : "+resultSet.getString("name"));
                System.out.println("Passenger Age   : "+resultSet.getInt("age"));
                System.out.println("Bus Number      : "+resultSet.getInt("bus_number"));
                System.out.println("Amount          : "+resultSet.getDouble("amount"));
                System.out.println("Booking Date    : "+resultSet.getString("booking_date"));
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

    /**
     * Searches a passenger by passenger ID.
     */
    public Customer searchCustomer(int passengerId) {
    String query = "SELECT * FROM customer WHERE passenger_id = ?";

    try {

        Connection connection = DBConnection.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, passengerId);
        ResultSet resultSet = prepareStatement.executeQuery();

        if (resultSet.next()) {

            Customer customer = new Customer(
                resultSet.getInt("passenger_id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getInt("bus_number"),
                resultSet.getString("booking_date"),
                resultSet.getDouble("amount"),
                resultSet.getString("starting_point"),
                resultSet.getString("ending_point")
                );

            resultSet.close();
            prepareStatement.close();
            connection.close();

            return customer;

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

    /**
    * Cancels a booked ticket and restores the seat.
    */
    public void cancelTicket(int passengerId) {

        Customer customer = searchCustomer(passengerId);
        if (customer == null) {
            System.out.println("Passenger Not Found.");
            return;
        }

        Bus bus = busDAO.searchBus(customer.getBusNumber());

        if (bus == null) {
            System.out.println("Bus Not Found.");
            return;
        }

        String query = "DELETE FROM customer WHERE passenger_id = ?";

        try {

            Connection connection = DBConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, passengerId);

            int rows = prepareStatement.executeUpdate();
            if (rows > 0) {
                busDAO.updateSeats(bus.getBusNumber(), bus.getSeatsAvailable() + 1);
                System.out.println("Ticket Cancelled Successfully.");
            } else {
                System.out.println("Cancellation Failed.");
            }

            prepareStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}