# Prescription Management System

## Overview
A web-based prescription management system built with Spring Boot.

## Features
- User authentication
- Prescription CRUD operations
- Date range filtering
- REST API
## Technologies Used
- Java 21
- Spring Boot 3.5.5
- Spring Security
- Spring Data JPA
- H2 Database
- Thymeleaf

## Setup Instructions
1. Clone the repository
2. Run `./mvnw spring-boot:run`
3. Access http://localhost:8080
4. signup
5. Login

## API Endpoints
- GET /api/v1/prescription - Get all prescriptions
- GET /api/v1/external-data - Fetch external API data [ Right now not functional ]

## Database
H2 in-memory database accessible at /h2-console
