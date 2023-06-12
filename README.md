# Library Application Backend

This repository contains the backend code for the Library Application.

## Prerequisites

Before running the backend, ensure that you have the following software and configurations in place:

- Java Development Kit (JDK): Verify that JDK is installed by running the command `java -version` in the terminal.
- Apache Maven: Verify that Maven is installed by running the command `mvn -v` in the terminal.
- MySQL Database: Set up a MySQL database with an empty schema called 'librarydb' and ensure it's running on `localhost:3306`. You can modify the database connection settings in the 'application.properties' file.

## Getting Started

To run the backend, follow these steps:

1. Clone this repository to your local machine.
2. Open a terminal and navigate to the root folder of the backend project.
3. Clean the project by running the command: `mvn clean`
4. Build the project by running the command: `mvn install`
5. Start the backend server by running the command: `mvn spring-boot:run`

## Customizing the Port

By default, the backend runs on `localhost:8080`. If you want to change the port, you can modify the 'server.port' property in the 'application.properties' file.

## Cross-Origin Resource Sharing (CORS)

To avoid Cross-Origin errors when calling the controllers from the frontend, the backend allows requests from `localhost:4200` by default. You can modify the '@CrossOrigin' annotation in the controllers located in the 'com.example.Library.controller' package to specify different allowed origins.

## Database Configuration

The backend connects to the MySQL database using the credentials 'username: root' and 'password: root' by default. You can modify the 'spring.datasource.username' and 'spring.datasource.password' properties in the 'application.properties' file to match your database credentials.

## Data Initialization

On program startup, the 'data.sql' file is loaded, which serves two purposes:

1. For presentation purposes: It loads initial data into the 'librarydb' schema. You can find the relevant sections in the 'data.sql' file, such as '-- Drop Triggers', '-- Delete Table Contents', '-- Insert Authors', '-- Insert Books', '-- Insert Rentals', and '-- Set Autoincrement to fitting ID'. You can comment out or delete these sections if you don't want to clear all data on project startup. Make sure to drop all 6 tables (including the ...-seq tables) before restarting the application.
2. Important part of the regular application: It inserts triggers into the 'librarydb' schema. You can find the '-- Create Trigger' section in the 'data.sql' file. Be sure not to comment out or delete these.

Make sure to adjust these settings according to your requirements.
