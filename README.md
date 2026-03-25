# Event Booking Platform

## Overview

The Event Booking Platform is a full-stack web application designed to manage the complete lifecycle of events, from creation to ticket booking and history tracking. The system allows users to explore available events, reserve tickets, and view their bookings, while administrators can manage events efficiently.

This project demonstrates practical implementation of RESTful APIs, database integration, and a responsive user interface using modern web technologies.

---

## Key Features

### User Features

* Browse all available events with details such as date, venue, and pricing
* Search events by title, venue, or description
* Book tickets based on availability
* View personal booking history
* Real-time seat availability updates

### Admin Features

* Create new events
* Delete events
* Manage event details and availability

---

## Technology Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA (Hibernate)
* REST API architecture

### Frontend

* HTML5
* CSS3 (Custom styling with variables and responsive layout)
* JavaScript (Vanilla JS for API integration)

### Database

* MySQL

### Tools & Environment

* Eclipse IDE
* MySQL Workbench / CMD
* Git & GitHub

---

## System Architecture

The application follows a layered architecture:

* **Controller Layer**: Handles HTTP requests and responses
* **Service Layer**: Contains business logic
* **Repository Layer**: Manages database operations
* **Entity Layer**: Represents database tables

Frontend communicates with backend through REST APIs using HTTP methods (GET, POST, DELETE).

---

## Application Workflow

1. User accesses the application through the browser
2. Frontend sends API requests to the backend
3. Backend processes the request using business logic
4. Data is stored or retrieved from MySQL database
5. Response is returned to frontend and displayed to the user

---

## Database Design

The application uses the following main tables:

* **users** → stores user details
* **events** → stores event information
* **bookings** → stores booking records

Relationships:

* One user can have multiple bookings
* One event can have multiple bookings

---

## Project Structure

```
src/main/java/com/eventapp/
 ├── controller/
 ├── service/
 ├── repository/
 ├── entity/
 ├── dto/

src/main/resources/
 ├── static/
 │    ├── css/
 │    ├── js/
 │    ├── html files
 ├── application.properties
```

---

## How to Run the Project

1. Clone the repository

   ```
   git clone https://github.com/your-username/event-booking-platform.git
   ```

2. Open the project in Eclipse

3. Configure MySQL in `application.properties`

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/event_db_app
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. Run the Spring Boot application

5. Open browser and navigate to:

   ```
   http://localhost:8081
   ```

---

## Challenges Faced

* Handling session-based authentication between frontend and backend
* Managing real-time seat updates during booking
* Debugging API integration issues (network errors, session handling)
* Ensuring proper data flow between layers

---

## Future Enhancements

* Payment gateway integration
* Email confirmation for bookings
* JWT-based authentication
* Cloud deployment (AWS / Azure)
* Mobile responsiveness improvements

---

## Conclusion

This project provides a complete understanding of building a full-stack application using Spring Boot and MySQL. It highlights key concepts such as REST API development, database relationships, and frontend-backend integration.

---

## Author

Hanimi Reddy
