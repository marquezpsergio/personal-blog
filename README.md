# Personal Blog Backend

This project is a robust backend application for a personal blog, built with Spring Boot. It provides a RESTful API for handling the creation, viewing, and interaction with blog posts.

## Features

- Create and retrieve blog posts
- Search posts by title
- View individual post details
- Like posts
- Comment on posts
- Handle development and production profiles

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- JUnit and Mockito for testing

## Getting Started

### Prerequisites

- Java JDK 21
- Maven
- PostgreSQL

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/your-username/personal-blog-backend.git
   ```

2. Navigate to the project directory:
   ```
   cd personal-blog-backend
   ```

3. Build the project:
   ```
   mvn clean install
   ```

### Database Configuration

Ensure you have PostgreSQL installed and configured. Then, update the `application.yml` file with your database credentials:

```
yaml:src/main/resources/application.yml
```

### Running the Application

Run the application using Maven:

```
mvn spring-boot:run
```

The application will be available at `http://localhost:8080/blog`.

## Project Structure

- `src/main/java/dev/sergiomarquez/blog`: Contains the main source code
  - `post`: Handles the logic related to blog posts
  - `comment`: Handles the logic related to comments
  - `utils`: Contains general utilities

## Key Endpoints

- `POST /api/posts`: Creates a new blog post
- `GET /api/posts`: Retrieves all blog posts
- `GET /api/posts/{postId}`: Retrieves a specific blog post
- `PATCH /api/posts/{postId}/like`: Likes a post
- `GET /api/posts/search/{title}`: Searches posts by title
- `POST /api/comments/create`: Creates a new comment
- `GET /api/comments/post/{postId}`: Retrieves comments for a specific post

## Testing

Run unit tests with:

```
mvn test
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.
