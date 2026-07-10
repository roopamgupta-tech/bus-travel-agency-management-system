-- ===================================================
-- Bus Travel Agency Management System Database
-- Author : Roopam Gupta
-- ===================================================

-- Create Database
CREATE DATABASE IF NOT EXISTS BusTravelAgency;

-- Use Database
USE BusTravelAgency;
-- ===========================
-- Bus Table
-- ===========================
CREATE TABLE Bus(
    bus_number INT PRIMARY KEY,
    booking_date DATE  NOT NULL,
    seat_capacity INT NOT NULL,
    seats_available INT NOT NULL,
    starting_point VARCHAR(50) NOT NULL,
    ending_point VARCHAR(50) NOT NULL
);
-- ===========================
-- Customer Table
-- ===========================
CREATE TABLE Customer(
    passenger_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    bus_number INT NOT NULL,
    booking_date DATE NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    starting_point VARCHAR(50) NOT NULL,
    ending_point VARCHAR(50) NOT NULL,

    FOREIGN KEY(bus_number)
    REFERENCES Bus(bus_number)
    ON DELETE RESTRICT
ON UPDATE CASCADE
);
-- ===========================
-- Driver Table
-- ===========================
CREATE TABLE Driver(
    driver_id INT PRIMARY KEY,
    driver_name VARCHAR(50) NOT NULL,
    bus_number INT NOT NULL UNIQUE,

    FOREIGN KEY(bus_number)
    REFERENCES Bus(bus_number)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);

-- ===========================
-- Sample Data
-- ===========================

INSERT INTO Bus VALUES
(101,'2026-07-15',40,40,'Delhi','Jaipur'),
(102,'2026-07-16',35,35,'Lucknow','Kanpur');

INSERT INTO Driver VALUES
(1,'Ramesh',101),
(2,'Suresh',102);

INSERT INTO Customer VALUES
(1001,'Amit',25,101,'2026-07-15',500,'Delhi','Jaipur');