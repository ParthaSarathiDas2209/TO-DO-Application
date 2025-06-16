ToDo Application (Thymeleaf + Spring Boot REST API, CRUD Controller)
A dynamic and intuitive full-stack ToDo application, built with Spring Boot for a robust backend and Thymeleaf for a rich, server-rendered user interface. This project efficiently demonstrates the implementation of RESTful APIs and a comprehensive CRUD controller for seamless task management.

-----
About the Project
This ToDo application serves as a practical demonstration of building a full-stack web application using the Spring ecosystem. It features a robust Spring Boot backend that exposes RESTful APIs to perform comprehensive CRUD operations (Create, Read, Update, Delete) on tasks.

The frontend is powered by Thymeleaf, providing a dynamic and server-rendered user interface that interacts seamlessly with the backend. The application emphasizes clean architecture, a well-defined CRUD controller (TaskController), and a responsive, user-friendly design. It includes essential features like task filtering, sorting, and client-side validations for a complete experience.

Features
This application offers a rich set of functionalities to manage your tasks effectively:

Comprehensive Task Management (CRUD Operations):

Create Tasks: Easily add new tasks with a title, assign a priority (Low, Medium, High), and set a specific due date.
View Tasks: Display all tasks, with the ability to filter by priority and sort by due date for organized viewing.
Edit Tasks: Update existing task details, including title, priority, due date, and completion status, through a dedicated edit page.
Delete Tasks: Individual tasks can be removed from the list with a smooth fade-out animation.
Dynamic Task Status Toggling: A simple click lets you instantly mark tasks as "completed" or "incomplete," with visual feedback (like a strikethrough for completed tasks).

Intelligent Date Display: Due dates are automatically converted and displayed in a user-friendly format (e.g., "Today," "Tomorrow," or "Overdue"), enhancing readability and quick understanding.

Robust Validation:

Server-Side Validation: Prevents users from setting due dates in the past during both task creation and editing, ensuring data integrity.
Client-Side Validation: The date input field is configured via JavaScript to disable past dates directly in the browser, improving user experience.
Persistent Data Storage: Tasks are reliably stored using Spring Data JPA with an H2 in-memory database. This ensures that your data persists across application restarts during development.

Responsive and Attractive User Interface:

Designed with modern CSS techniques including Flexbox for flexible layouts, attractive gradients for backgrounds, and custom button styles.
Includes subtle animations (e.g., fade-out on task deletion) for an enhanced and engaging user experience.
Utilizes media queries to ensure the application is fully responsive and looks great on various screen sizes (e.g., mobile, tablet, desktop).
Integrates Bootstrap 5 for consistent styling and readily available responsive components.
Clear User Feedback: Implements flash messages (success and error alerts) to provide immediate, context-aware feedback to the user after actions (e.g., "Task created successfully!", "Due date cannot be in the past.").

Technologies Used
Backend:
Java 17: The core programming language.
Spring Boot 3.x.x: Framework for building robust, stand-alone, production-grade Spring applications.
Spring Data JPA: Simplifies data access using JPA (Java Persistence API).
Hibernate: JPA implementation for ORM (Object-Relational Mapping).
H2 Database: An in-memory relational database used for development and testing. Easily switchable to other SQL databases.
Lombok: Reduces boilerplate code (e.g., getters, setters) for models.
Maven: Dependency management and build automation tool.
Frontend:
Thymeleaf: A natural templating engine for web and standalone environments, tightly integrated with Spring.
HTML5: Standard markup language for structuring web content.
CSS3: Custom styling with Flexbox, Animations, and Media Queries for responsive design.
Bootstrap 5: Popular CSS framework for developing responsive and mobile-first websites.
JavaScript: Minimal client-side scripting for UI interactions (e.g., date input constraints, delete animations).
Version Control:
Git: Distributed version control system.
GitHub: Platform for hosting and collaborating on code.
