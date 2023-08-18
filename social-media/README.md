# Social Media

[![Backend build](https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/actions/workflows/build.yaml/badge.svg)](https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/actions/workflows/build.yaml)
<svg fill="none" viewBox="0 0 120 120" width="120" height="120" xmlns="http://www.w3.org/2000/svg">
   <foreignObject width="100%" height="100%">
      <div xmlns="http://www.w3.org/1999/xhtml">
         <img alt="Coverage" src="https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/blob/master/.github/badges/task3-jr-coverage.svg"/>
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
   git clone https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course.git
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

## Feedback
**1. Was it easy to complete the task using AI?**

ChatGPT simplifies the process of creating basic classes. Also, it can assist in generating tests. 
However, ChatGPT may produce errors, and the responses it provides should be carefully reviewed and controlled by the developer.

**2. How long did task take you to complete?**

I spent about 2.5-3 hours to complete this tasks.

**3. Was the code ready to run after generation? What did you have to change to make it usable?**

I did not change 80-90% of code  at all. I added my changes into domain (model) classes, configurations, tests and services.

**4. Which challenges did you face during completion of the task?**

For example, I regenerated the question several times so that chatgpt would provide me services with all implemented
methods until I had an acceptable result.  

**5. Which specific prompts you learned as a good practice to complete the task?**

It's better to generate questions step-by-step to get comprehensive and correct results.