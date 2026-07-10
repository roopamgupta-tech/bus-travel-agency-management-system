package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.BusDao;
import dao.CustomerDao;
import dao.DriverDao;

import model.Bus;
import model.Customer;
import model.Driver;

/**
 * Main class of the Bus Travel Agency Management System.
 * Provides menu-driven interaction with the user.
 */
public class Main {

    public static void main(String[] args) {

        BusDao busDao = new BusDao();
        CustomerDao customerDao = new CustomerDao();
        DriverDao driverDao = new DriverDao();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("========== BUS TRAVEL AGENCY ==========\r\n" + //
                                "1. Add Bus\r\n" + //
                                "2. Show All Buses\r\n" + //
                                "3. Search Bus\r\n" + //
                                "4. Update Seats\r\n" + //
                                "5. Delete Bus\r\n" + //
                                "6. Book Ticket\r\n" + //
                                "7. Show All Customers\r\n" + //
                                "8. Search Customer\r\n" + //
                                "9. Cancel Ticket\r\n" + //
                                "10. Add Driver\r\n" + //
                                "11. Show All Drivers\r\n" + //
                                "12. Search Driver\r\n" + //
                                "13. Search Driver By Bus Number\r\n" + //
                                "14. Delete Driver\r\n" + //
                                "15. Exit\r\n" + //
                                "=======================================\r\n" + //
                                "Enter Choice:");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1": {
                        // Add Bus
                    System.out.print("Enter Bus Number : ");
                        int busNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Booking Date : ");
                        String bookingDate = scanner.nextLine();
                        System.out.print("Enter Seat Capacity : ");
                        int seatCapacity = scanner.nextInt();
                        System.out.print("Enter Available Seats : ");
                        int seatsAvailable = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Enter Starting Point : ");
                        String startingPoint = scanner.nextLine();
                        System.out.print("Enter Ending Point : ");
                        String endingPoint = scanner.nextLine();
                        
                        Bus bus = new Bus(busNumber, seatCapacity, seatsAvailable, startingPoint, endingPoint, bookingDate);
                        busDao.addBus(bus);

                        break;
                    }
                    case "2": {
                        // Show All Buses
                        busDao.showAllBuses();
                        break;
                    }
                    case "3": {
                        // Search Bus
                        System.out.print("Enter Bus Number : ");
                        int busNumber = scanner.nextInt();
                        scanner.nextLine();
                        Bus bus = busDao.searchBus(busNumber);

                        if (bus != null) {
                            System.out.println("Bus Number      : " + bus.getBusNumber());
                            System.out.println("Booking Date    : " + bus.getBookingDate());
                            System.out.println("Seat Capacity   : " + bus.getSeatCapacity());
                            System.out.println("Available Seats : " + bus.getSeatsAvailable());
                            System.out.println("Starting Point  : " + bus.getStartingPoint());
                            System.out.println("Ending Point    : " + bus.getEndingPoint());
                        } else {
                            System.out.println("Bus Not Found.");
                        }
                        break;
                    }
                    case "4": {
                        // Update Seats
                        System.out.print("Enter Bus Number : ");
                        int busNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter New Available Seats : ");
                        int seatsAvailable = scanner.nextInt();
                        scanner.nextLine();

                        busDao.updateSeats(busNumber, seatsAvailable);
                        break;
                    }
                    case "5": {
                        // Delete Bus
                        System.out.print("Enter Bus Number : ");
                        int busNumber = scanner.nextInt();
                        scanner.nextLine();

                        busDao.deleteBus(busNumber);
                        break;
                    }
                    case "6": {
                        // Book Ticket
                        System.out.print("Enter Passenger ID : ");
                        int passengerId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Passenger Name : ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Age : ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Bus Number : ");
                        int busNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Booking Date : ");
                        String bookingDate = scanner.nextLine();
                        System.out.print("Enter Amount : ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Starting Point : ");
                        String startingPoint = scanner.nextLine();
                        System.out.print("Enter Ending Point : ");
                        String endingPoint = scanner.nextLine();

                        Customer customer = new Customer(passengerId, name, age, busNumber, bookingDate, amount, startingPoint, endingPoint);
                        customerDao.bookTicket(customer);
                        break;
                    }
                    case "7": {
                        // Show All Customers
                        customerDao.showAllCustomers();
                        break;
                    }
                    case "8": {
                        // Search Customer
                        System.out.print("Enter Passenger Id : ");
                        int passengerId = scanner.nextInt();
                        scanner.nextLine();

                        Customer customer = customerDao.searchCustomer(passengerId);
                        if (customer != null) {
                            System.out.println("Passenger ID    : " + customer.getPassengerId());
                            System.out.println("Name            : " + customer.getName());
                            System.out.println("Age             : " + customer.getAge());
                            System.out.println("Bus Number      : " + customer.getBusNumber());
                            System.out.println("Booking Date    : " + customer.getBookingDate());
                            System.out.println("Amount          : " + customer.getAmount());
                            System.out.println("Starting Point  : " + customer.getStartingPoint());
                            System.out.println("Ending Point    : " + customer.getEndingPoint());
                        } else {
                            System.out.println("Passenger Not Found.");
                        }

                        break;
                    }
                    case "9": {
                        // Cancel Ticket
                        System.out.print("Enter Passenger Id : ");
                        int passengerId = scanner.nextInt();
                        scanner.nextLine();

                        customerDao.cancelTicket(passengerId);
                        break;
                    }
                    case "10": {
                        System.out.print("Enter Driver ID : ");
                        int driverId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Driver Name : ");
                        String driverName = scanner.nextLine();
                        System.out.print("Enter Bus Number : ");
                        int busNumber = scanner.nextInt();
                        scanner.nextLine();

                        Driver driver = new Driver(driverName, busNumber, driverId);
                        driverDao.addDriver(driver);

                        break;
                    }
                    case "11": {
                        // Show All Drivers
                        driverDao.showAllDrivers();
                        break;
                    }
                    case "12": {
                        System.out.print("Enter Driver ID : ");
                        int driverId = scanner.nextInt();
                        scanner.nextLine();

                        Driver driver = driverDao.searchDriver(driverId);

                        if (driver != null) {
                            System.out.println("Driver ID   : " + driver.getDriverId());
                            System.out.println("Driver Name : " + driver.getDriverName());
                            System.out.println("Bus Number  : " + driver.getBusNumber());
                        } else {
                            System.out.println("Driver Not Found.");
                        }

                        break;
                    }
                    case "13": {
                        System.out.print("Enter Bus Number : ");
                        int busNumber = scanner.nextInt();
                        scanner.nextLine();

                        Driver driver = driverDao.searchDriverByBus(busNumber);

                        if (driver != null) {
                            System.out.println("Driver ID   : " + driver.getDriverId());
                            System.out.println("Driver Name : " + driver.getDriverName());
                            System.out.println("Bus Number  : " + driver.getBusNumber());
                        } else {
                            System.out.println("Driver Not Found.");
                        }

                        break;
                    }
                    case "14": {
                        System.out.print("Enter Driver ID : ");
                        int driverId = scanner.nextInt();
                        scanner.nextLine();

                        driverDao.deleteDriver(driverId);
                        break;
                    }
                    case "15": {
                        // Exit
                        System.out.println("Thank You!");
                        scanner.close();
                        return;
                    }
                    default:
                        System.out.println("Wrong Choice.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter valid input.");
                scanner.nextLine();
                continue;

            }

        }

    }
}