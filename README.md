# 🚌 Bus Travel Agency Management System

A console-based **Bus Travel Agency Management System** developed using **Java, JDBC, and MySQL**. This project demonstrates CRUD operations, database connectivity, input validation, and object-oriented programming concepts through a real-world bus reservation management system.

---

## 📌 Features

### 🚌 Bus Management

* Add a new bus
* View all buses
* Search bus by bus number
* Update available seats
* Delete bus
* Prevent duplicate bus numbers
* Validate seat capacity and available seats

### 👨‍✈️ Driver Management

* Add driver
* View all drivers
* Search driver by ID
* Delete driver
* Prevent duplicate driver IDs
* Ensure one driver is assigned to only one bus
* Validate bus existence before driver assignment

### 🎫 Customer Management

* Book ticket
* View all customers
* Search customer by passenger ID
* Cancel ticket
* Automatic seat update after booking/cancellation
* Prevent duplicate passenger IDs
* Validate passenger details before booking

---

## 🛠️ Technologies Used

* Java
* JDBC
* MySQL
* VS Code / Eclipse
* Git & GitHub

---

## 📂 Project Structure

```
BusTravelAgency/
│
├── src/
│   ├── dao/
│   │   ├── BusDao.java
│   │   ├── DriverDao.java
│   │   └── CustomerDao.java
│   │
│   ├── model/
│   │   ├── Bus.java
│   │   ├── Driver.java
│   │   └── Customer.java
│   │
│   ├── util/
│   │   └── DBConnection.java
│   │
│   └── Main.java
│
├── lib/
│   └── mysql-connector-j-9.7.0.jar
|
├── database.sql
├── README.md
└── .gitignore
```

---

## 🗄️ Database Design

The project uses three relational tables:

* **Bus**
* **Driver**
* **Customer**

### Relationships

* One Bus → One Driver
* One Bus → Many Customers

Foreign key constraints are used to maintain data integrity.

---

## ✅ Validations Implemented

### Bus

* Duplicate bus number check
* Seat capacity must be greater than zero
* Available seats cannot exceed seat capacity
* Source and destination cannot be the same
* Date format validation
* Empty field validation

### Driver

* Duplicate driver ID check
* One driver per bus
* Bus existence validation
* Empty name validation

### Customer

* Duplicate passenger ID check
* Bus availability check
* Seat availability validation
* Age validation
* Amount validation
* Source and destination validation
* Booking date validation
* Empty field validation

---

## 🚀 How to Run

### 1. Clone the repository

```bash
git clone https://github.com/roopamgupta-tech/BusTravelAgency.git
```

### 2. Create the database

Run the `database.sql` file in MySQL.

### 3. Configure database connection

Update your MySQL credentials in:

```
util/DBConnection.java
```

### 4. Run the project

Compile and execute `Main.java`.

---

## 📷 Screenshots

You can add screenshots here after uploading them.

* Main Menu
* Add Bus
* Book Ticket
* View Customers
* View Drivers
* View Buses

---

## 🎯 Learning Outcomes

This project helped me understand:

* JDBC Connectivity
* CRUD Operations
* Object-Oriented Programming
* DAO Design Pattern
* MySQL Database Design
* Input Validation
* Exception Handling
* Foreign Keys and Constraints

---

## 🔮 Future Improvements

* Login Authentication
* Admin Dashboard
* Seat Selection
* Ticket Generation (PDF)
* Fare Calculation
* Search by Route
* Journey History
* GUI using JavaFX/Swing
* REST API Integration

---

## 👨‍💻 Author

**Roopam Gupta**

GitHub: https://github.com/roopamgupta-tech
LinkedIn: https://www.linkedin.com/in/roopam-gupta-586208225/
---

## 📄 License

This project is created for educational and learning purposes.
