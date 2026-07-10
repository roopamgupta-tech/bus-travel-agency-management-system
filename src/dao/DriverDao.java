package dao;

// DAO class for Driver database operations

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Bus;
import model.Driver;
import util.DBConnection;
/**
 * DriverDao class performs CRUD operations
 * for Driver records.
 */
public class DriverDao {
    

    /**
     * Adds a driver to a bus.
     */
    public void addDriver(Driver driver) {

        if (searchDriver(driver.getDriverId()) != null) {
            System.out.println("Driver ID Already Exists.");
            return;
        }

        if (searchDriverByBus(driver.getBusNumber()) != null) {
            System.out.println("Driver Already Assigned To This Bus.");
            return;
        }

        if (driver.getDriverName().trim().isEmpty()) {
            System.out.println("Driver Name cannot be empty.");
            return;
        }

        BusDao busDao = new BusDao();
        Bus bus = busDao.searchBus(driver.getBusNumber());
        if (bus == null) {
            System.out.println("Bus Not Found.");
            return;
        }
        String query = "INSERT INTO Driver VALUES(?,?,?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, driver.getDriverId());
            prepareStatement.setString(2, driver.getDriverName());
            prepareStatement.setInt(3, driver.getBusNumber());

            int rows = prepareStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Driver Added Successfully.");
            } else {
                System.out.println("Driver Not Added.");
            }

            prepareStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays all drivers.
     */
    public void showAllDrivers() {
        String query = "SELECT * FROM Driver";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                
                System.out.println("Driver ID   : "+resultSet.getInt("driver_id"));
                System.out.println("Driver Name : "+resultSet.getString("driver_name"));
                System.out.println("Bus Number  : "+resultSet.getInt("bus_number"));
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
     * Searches a driver by driver ID.
     */

    public Driver searchDriver(int driverId) {

        String query = "SELECT * FROM Driver WHERE driver_id = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, driverId);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                
                Driver driver = new Driver(
                    resultSet.getString("driver_name"),
                    resultSet.getInt("bus_number"),
                    resultSet.getInt("driver_id")
                );
                resultSet.close();
                prepareStatement.close();
                connection.close();

                return driver;
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
     * Deletes a driver record.
     */
    public void deleteDriver(int driverId) {

        Driver driver = searchDriver(driverId);

        if (driver == null) {
            System.out.println("Driver Not Found.");
            return;
        }
        
        String query = "DELETE FROM Driver WHERE driver_id = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, driverId);
            int rows = prepareStatement.executeUpdate();

            if(rows > 0){
                System.out.println("Driver Deleted Successfully.");
            }
            else{
                System.out.println("Driver Not Found.");
            }
            
            prepareStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches the driver assigned to a specific bus.
     */
    public Driver searchDriverByBus(int busNumber) {

        String query = "SELECT * FROM Driver WHERE bus_number = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, busNumber);
            ResultSet resultSet = prepareStatement.executeQuery();

            if(resultSet.next()){
                Driver driver = new Driver(
                        resultSet.getString("driver_name"),
                        resultSet.getInt("bus_number"),
                        resultSet.getInt("driver_id")
                );

                resultSet.close();
                prepareStatement.close();
                connection.close();
                return driver;
            }

            resultSet.close();
            prepareStatement.close();
            connection.close();

        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
