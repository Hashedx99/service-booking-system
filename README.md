# Service Booking System

## Project Description

The Service Booking System is a web application designed to facilitate the booking and management of property and
service appointments. It supports multiple user types, secure authentication, and scheduling features for both property
and service providers.

## Tools and Technologies

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security (JWT)
* Maven
* Thymeleaf (for email templates)
* Postman (for API testing)
* Git & GitHub

## General Approach

The project was developed using a layered architecture, separating concerns between controllers, services,
repositories, and models. Security was implemented using JWT tokens, and user roles were enforced throughout the
application. The database schema was designed using an ERD, and user stories guided the development of features.
Continuous integration was maintained via GitHub, and deliverables were tracked using JIRA.

## API Endpoints

| Endpoint                              | HTTP Method | Description                          |
|---------------------------------------|-------------|--------------------------------------|
| `/auth/users/register`                | POST        | Register a new user                 |
| `/auth/users/login`                   | POST        | Authenticate user and get JWT token |
| `/api/properties`                     | GET         | Get all properties                  |
| `/api/properties/{id}`                | GET         | Get property by ID                  |
| `/api/property-bookings`              | POST        | Create a new property booking       |
| `/api/service-bookings`               | POST        | Create a new service booking        |

For a complete list of endpoints, refer to the [Postman Collection](https://www.postman.com/workspace/Project2~ae428d20-95fd-432b-bcf8-a91bfa3f0399/collection/50802721-80d2c4e1-8cdb-4dba-9629-f019b2481530).


## Major Hurdles & Unsolved Problems

* Error Handling: Ensuring consistent error responses across all endpoints was challenging, especially for
  authorization and validation errors.
* User Association: Guaranteeing that entities like Property and Service always have a non-null User reference
  required careful validation and service logic.

## Known Issues

* Some endpoints may not handle edge cases for invalid data types.
* The application currently lacks rate-limiting for API requests.

## Documentation Links

* ERD Diagram: [ERD Diagram](https://i.imgur.com/pekI9DI.png)
* Planning & Scope: [JIRA Board](https://hshabx99.atlassian.net/jira/software/projects/SRS/boards/1)
* API Documentation: [Postman Collection](https://www.postman.com/workspace/Project2~ae428d20-95fd-432b-bcf8-a91bfa3f0399/collection/50802721-80d2c4e1-8cdb-4dba-9629-f019b2481530)

## Setup Instructions

* Prerequisites:
    - Java 17
    - Maven
    - PostgreSQL Database
* Steps:
* create a PostgreSQL database and update the `run configuration` with your database credentials.
* use these environment variables
  - `DB_PASSWORD=12345678;DB_URL=jdbc:postgresql://localhost:5432/service-booking-system;DB_USERNAME=postgres;MY_EMAIL=b.mojtabaalilol@gmail.com;JWT_SECRET=C6UlILsE6GJwNqwCTkkvJj9O653yJUoteWMLfYyrc3vaGrrTOrJFAUD1wEBnnposzcQl`
  - change the db values according to your local database setup.
  - setup an email account for sending emails and update the `MY_EMAIL` and `API_KEY` variable accordingly.
  - Create a cloudinary account and get your credentials, then update the `run configuration` with your cloudinary 
    credentials. `API_CLOUDNAME={};API_SECRET={};CLOUDINARY_KEY={};`
* build the project using Maven: `mvn clean install`
* run the application
* you can contact me for run configuration setup if needed.

## Testing Instructions

1. Import the Postman collection linked above.
2. Set up the environment variable `baseUrl` in Postman.
3. Run the requests in the collection to test the API.

## Acknowledgments

* Spring Boot documentation
* Postman for API testing
* Cloudinary for media storage
* Thymeleaf for email templates