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

## Major Hurdles & Unsolved Problems
* Error Handling: Ensuring consistent error responses across all endpoints was challenging, especially for 
  authorization and validation errors.
* User Association: Guaranteeing that entities like Property and Service always have a non-null User reference 
  required careful validation and service logic.

## Documentation Links
* ERD Diagram: [ERD Diagram](https://i.imgur.com/pekI9DI.png)
* Planning & Scope: [JIRA Board](https://hshabx99.atlassian.net/jira/software/projects/SRS/boards/1)
* API Documentation: [Postman Collection]()