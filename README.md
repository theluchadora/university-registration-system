# University Registration System

## Description
This project is a University Registration System built using Java with a Swing GUI, integrated with a MySQL database via XAMPP for backend storage. The application allows admins to register, view students’ lists, and manipulate those records. The higher admins can register admins, view admins’ lists, and manipulate those records. The students can view their profiles, update their information, and change passwords.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven
- XAMPP (for MySQL database)

## Project Structure
your-project/
 ├── src/
 │ ├── main/
 │ │ ├── java/
 │ │ │ └── edu/university/
 │ │ └── resources/
 │ └── test/
 │ └── java/
 ├── pom.xml
 ├── README.md
 └── your_database_dump.sql

## Setup Instructions

### Database Setup
1. Open phpMyAdmin (usually accessible at `http://localhost/phpmyadmin`).
2. Create a new database named `universityregistration`.
3. Go to the "Import" tab and upload the `universityregistration.sql` file included in this project.

### Running the Application
1. Navigate to the project directory.
2. Open a terminal and run the following command to build the project:
   ```bash
   mvn clean install

Execute the following command to run the application:

mvn exec:java -Dexec.mainClass="edu.university.App

use the login criedentials given below to log into the system.

### Login Credentials

Admin Login
Username: A/001
Password: 0101

Higher Admin Login
Username: HA/001
Password: 1001

Student Login
Username: UGR/001
Password: 0001

## Features
User Registration: Students can be registered by admin, and higher admin can register admin.
Profile Management: Students can view and update their profile information.
Password Management: Students can change their passwords securely.
Student Management: Admin can view students’ lists and manipulate student records.
Admin Management: The higher admin can view the admins' lists and manipulate the admin’s record.
User Logout: Users can log out of the application.

## Technologies Used
Java
Swing (for GUI)
MySQL (via XAMPP)
Maven (for dependency management)

## Additional Notes
Ensure that the XAMPP server is running and the MySQL service is active before starting the application.
Adjust the database connection settings in your Java code as necessary, depending on your XAMPP setup.




