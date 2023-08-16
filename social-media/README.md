# Social Media

[![Build](https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/tree/master/social-media/actions/workflows/build.yaml/badge.svg)](https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/tree/master/social-media/actions/workflows/build.yaml)
<svg fill="none" viewBox="0 0 120 120" width="120" height="120" xmlns="http://www.w3.org/2000/svg">
   <foreignObject width="100%" height="100%">
      <div xmlns="http://www.w3.org/1999/xhtml">
         <img alt="Coverage" src="https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/tree/master/social-media/.github/badges/coverage.svg"/>
      </div>
   </foreignObject>
</svg>

This is a simple social media application built using Spring Boot, Hibernate, and PostgreSQL.

## Prerequisites

- Java 11 or higher
- PostgreSQL database
- Apache Maven

## Getting Started

1. Clone the repository:

   ```sh
   git clone https://github.com/yourusername/social-media-app.git
   ```
   
2. Navigate to the project directory:
    ```sh
   cd social-media-app
    ```
   
3. Create a PostgreSQL database named '_**socialmedia**_' (you can change the name in **application.properties**).

4. Configure Database Settings:

    Open **src/main/resources/application.properties** and update the following properties with your PostgreSQL settings:
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/socialmedia
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

5. Build and run the application:
    ```sh
   mvn spring-boot:run
   ```

The application will start and listen on http://localhost:8080.