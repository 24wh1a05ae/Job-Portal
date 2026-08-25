# Job Portal

## Project Overview

The Job Portal is a Java-based application that connects employers and candidates. Employers can post job opportunities, while candidates can view available jobs and apply for suitable positions.

The application uses Java for the application logic and MySQL for storing user, job, and application data.

## Features

- User registration and login
- Role-based access for users
- Employer job posting
- Job listing and viewing
- Candidate job applications
- Application status tracking
- MySQL database integration
- Logout functionality

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- Eclipse IDE

## Java Concepts Used

- Object-Oriented Programming
- Classes and Objects
- Encapsulation
- Exception Handling
- Event Handling
- JDBC
- SQL database operations

## Database Integration

The project uses MySQL database named `job_portal`.

The database contains the following tables:

- `users`
- `jobs`
- `applications`

The SQL file required to create the database and tables is available in:

`database/jobportal.sql`

## Database Structure

### Users

Stores user login information and roles.

### Jobs

Stores job details such as:

- Job title
- Company name
- Location
- Salary
- Job type
- Description
- Required skills
- Employer

### Applications

Stores candidate applications for jobs, including application status and application date.

## How to Run

1. Install Java and MySQL.
2. Open the project in Eclipse.
3. Create/import the `job_portal` database using `database/jobportal.sql`.
4. Configure the MySQL username and password in `DBConnection.java`.
5. Add the MySQL Connector/J library to the project.
6. Clean and build the project.
7. Run the `Main.java` file.

## Project Structure

```text
JobPortal
├── src
│   └── com
│       └── inventory
│           ├── dao
│           ├── database
│           ├── gui
│           └── model
│
├── database
│   └── jobportal.sql
│
├── screenshots
│
└── README.md