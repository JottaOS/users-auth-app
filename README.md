# Users Auth App

This repository contains a user authentication application built with React, Spring Boot, and MySQL.

## Features

- User authentication with JWT and Spring Security
- Basic CRUD operations
- React hooks and Redux for state management
- JPA for database interaction

## Setup

1. Clone the repository.
2. Set up MySQL database connection in `application.properties`.
3. Run backend: `cd server && ./mvnw spring-boot:run`.
4. Install frontend dependencies: `cd client && npm install`.
5. Start frontend: `npm run dev`.

## API Endpoints

- **GET /api/users:** Get all users.
- **GET /api/users/{id}:** Get user by ID.
- **POST /api/users:** Create a new user.
- **PUT /api/users/{id}:** Update user by ID.
- **DELETE /api/users/{id}:** Delete user by ID.

## Technologies

- Frontend: React, Redux
- Backend: Spring Boot, Spring Security, JPA
- Database: MySQL
