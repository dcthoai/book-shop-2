# Full stack ecommerce bookstore website
![image](https://github.com/dcthoai/bookstore/assets/115138333/83cb09c9-af3b-41d5-986c-6eff85439673)

Explore a vast collection of books across all genres, easily find your favorite authors, and enjoy a seamless online shopping experience with convenient delivery.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Project architecture](#architecture)
- [License](#license)

## Features
- Key functionalities:
    - CRUD operations for library books.
    - User authentication and authorization.
    - Search and filtering of book catalog.
    - Shopping cart management
    - Checkout process 

## Technologies
- Frontend: HTML/CSS/Javascript + Bootstrap framework
- Backend: Spring MVC, JDBC, Spring Security + JWT, Maven
- Database: MySQL

# Architecture

The full-stack ecommerce bookstore website follows a three-tier architecture consisting of the following components:

## Frontend
- **Web Pages:** Home page, book catalog, shopping cart, checkout, user profile, and administration pages.
- **User Interactions:** Search, filtering, adding books to cart, checkout process, and user management.
- **API Consumption:** Making HTTP requests to the backend API to fetch book data, perform CRUD operations, and manage user authentication.

## Backend
- **Spring MVC:** Handles HTTP requests, maps URLs to controller methods, and manages the application flow.
- **JDBC:** Provides data access and integration with the MySQL database for book management and user information.
- **Spring Security:** Handles user authentication and authorization, ensuring secure access to the application's functionalities.
- **JWT (JSON Web Tokens):** Manages user session and authorization tokens for secure communication between the frontend and backend.
