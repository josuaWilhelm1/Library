# This is the backend for the Library Application


## To run this backend pls:
    - run 'mvn clean' in root folder (.../Library)
    - run 'mvn install' in root folder (.../Library)
    - run 'mvn spring-boot:run'  in root folder (.../Library)


## Default port for backend: 'localhost:8080'
    - this can be changed in the application.properties by adding 'server.port=8081'

## The Frontend has to run on localhost:4200 to avoid Cross Origin Error when calling the Controllers
    - this can be changed in the Controllers by changing '@CrossOrigin(origins = "http://localhost:4200")' in the 3 Controllers inside of the package 'com.example.Library.controller'

## A MySQL Database is needed to run the backend with a empty schema called: 'librarydb'.
    - the backend tries to connect to the db via 'localhost:3306'
      - the schema name and port can be changed in the application properties by changing 'spring.datasource.url=jdbc:mysql://localhost:3306/librarydb?serverTimezone=UTC&useSSL=false&autoReconnect=true'
    - the credentials for the MySQL DB are 'username':root', 'password:root'
      - the credentials can be changed by changing: 'spring.datasource.username=root' and 'spring.datasource.password=root' in the 'application.properties'

## On Program startup the data.sql file gets loaded. This file has 2 jobs:
    - For presentation purposes: load initial data into the DB
      - '-- Drop Triggers', '-- Delete Table Contents', '-- Insert Authors', '-- Insert Books', '-- Insert Rentals', '-- Set Autoincrement to fitting ID'
    - Important part of regular application: Inserts Triggers into the DB
    - '-- Create Trigger'
    - If you want to run the project without clearing all Data on Project Start and Item Load up pls delete or comment out the sections (-- Drop Triggers, -- Delete Table Contents, -- Insert Authors, -- Insert Books, -- Insert Rentals, -- Set Autoincrement to fitting ID)
        - Only the Section '-- Create Trigger' should be left (Warning commenting out with # does not work, use the "--" syntax)




