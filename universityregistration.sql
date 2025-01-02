-- XAMPP's MySQL Server Compatible Dump
-- Database: universityregistration

-- --------------------------------------------------------

-- Create and select database
CREATE DATABASE IF NOT EXISTS universityregistration;
USE universityregistration;

-- --------------------------------------------------------

-- Drop existing tables if they exist
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS higheradmin;
DROP TABLE IF EXISTS student;

-- --------------------------------------------------------

-- Table structure for table admin
CREATE TABLE admin (
  username VARCHAR(20) NOT NULL PRIMARY KEY,
  password VARCHAR(20) NOT NULL,
  fname VARCHAR(50) NOT NULL,
  lname VARCHAR(50) NOT NULL
);

-- Dumping data for table admin
INSERT INTO admin (username, password, fname, lname) VALUES
('A/001', '0101', 'adminFname', 'adminLname'),
('A/002', '0102', 'admintwoFname', 'adminLtwoname');

-- --------------------------------------------------------

-- Table structure for table higheradmin
CREATE TABLE higheradmin (
  username VARCHAR(50) NOT NULL PRIMARY KEY,
  password VARCHAR(50) NOT NULL,
  full_name VARCHAR(50) NOT NULL
);

-- Dumping data for table higheradmin
INSERT INTO higheradmin (username, password, full_name) VALUES
('HA/001', '1001', 'HA1Fname HA1Lname'),
('HA/002', '1002', 'HA2Fname HA2Lname');

-- --------------------------------------------------------

-- Table structure for table student
CREATE TABLE student (
  R_No INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  gender VARCHAR(15) NOT NULL,
  department VARCHAR(50) NOT NULL,
  bankacc VARCHAR(50) DEFAULT NULL,
  Phone_number VARCHAR(15) DEFAULT NULL
);

-- Dumping data for table student
INSERT INTO student (username, password, firstname, lastname, gender, department, bankacc, Phone_number) VALUES
('UGR/001', '0001', 'studentFname', 'studentLname', 'Male', 'Electrical Engineering', '1000', '0989898989'),
('UGR/002', '0002', 'studenttwoFname', 'studenttwoLname', 'Male', 'Computer Science', '1000000', '0900000000');

-- --------------------------------------------------------