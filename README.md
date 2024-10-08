
# Bike Service Hub Project Setup Document

## Project Overview
The Bike Service Hub is a web application designed to manage bike service bookings. It allows users to add their bikes, book services, and check the status of their service requests. Admins can manage bike bookings, confirm services, and mark bikes as ready for delivery.

## Requirements

### Software Requirements
1. Java JDK 8 or above: Required to run the backend server.
2. Apache Tomcat 9 or above: Serves as the web server for the application. [Download Link](https://tomcat.apache.org/download-90.cgi)
3. Oracle Database: Database server to store user and bike service details.
4. SQL Developer: Tool for managing the Oracle database.
5. Eclipse IDE: Integrated Development Environment for coding and managing the project.
6. JDBC Driver for Oracle: Required to connect Java applications to the Oracle database.

## Database Setup

### Create the Database and Tables

#### Create Database User:
```sql
CREATE USER bike_service_hub IDENTIFIED BY your_password;
GRANT CONNECT, RESOURCE TO bike_service_hub;
ALTER USER bike_service_hub QUOTA UNLIMITED ON users;
```

#### Create User Table:
```sql
CREATE TABLE users (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR2(50) NOT NULL UNIQUE,
    password VARCHAR2(255) NOT NULL,
    role VARCHAR2(10) NOT NULL
);
```

#### Create Bike Table:
```sql
CREATE TABLE bikes (
    bike_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    bike_model VARCHAR2(100) NOT NULL,
    bike_number VARCHAR2(20) NOT NULL,
    user_id NUMBER,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

#### Create Service Table:
```sql
CREATE TABLE services (
    service_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    bike_id NUMBER,
    service_date DATE NOT NULL,
    service_description CLOB NOT NULL,
    status VARCHAR2(20) NOT NULL DEFAULT 'Pending',
    FOREIGN KEY (bike_id) REFERENCES bikes(bike_id)
);
```

## Project Setup in Eclipse

### Create Project in Eclipse
1. Open Eclipse IDE.
2. Create a new Java Dynamic Web Project.

### Clone the Repository:
```bash
git clone https://github.com/Sundar0011/BikeServiceHub.git
```

### Open the Project in Eclipse

#### Add JDBC Driver:
1. Download the jar files from [this link](https://drive.google.com/drive/folders/1H-OW9I8T9X3A2JiGjR0dEFKBJPlycrI4?usp=drive_link).
2. Add the `ojdbc8.jar` file to your project’s `lib` directory and include it in the build path:
    - Right-click on the project in Eclipse.
    - Select Build Path > Configure Build Path.
    - Go to the Libraries tab and add the `ojdbc8.jar`.

### Configure the Database Connection:

#### Update UserDAO:
Path: `/BikeService/src/main/java/com/service/UserDAO.java`

- Change the database URL and username/password.
- Update all functions as needed.

#### Update SignupDAO:
Path: `/BikeService/src/main/java/com/service/SignupDAO.java`

- Change the database URL and username/password.
- Update all functions as needed.

### Replace Admin Database Password:
To change the Admin Password, open the Admin Login file:
Path: `/BikeService/src/main/java/com/service/AdminLogin.java`

- Admin Username: `sundarav61@gmail.com`
- Password: `sundar2003`

## Run Project

1. Right-click on `index.html` file (Path: `/BikeService/src/main/webapp/index.html`).
2. Click `Run`.
3. Click `Run on Server` and select the Tomcat Server.

Open a web browser and navigate to `http://localhost:8080/BikeServiceHub`.

## Conclusion
Follow the above steps to set up and run the Bike Service Hub project using an Oracle database and Java Dynamic Web Project. Ensure that all dependencies are properly installed and configured. For any issues or additional features, refer to the project documentation or community forums.

