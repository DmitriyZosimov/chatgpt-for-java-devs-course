# Simple Online Bookstore Application

[![Backend build](https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/actions/workflows/build.yaml/badge.svg)](https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/actions/workflows/build.yaml)
<svg fill="none" viewBox="0 0 120 120" width="120" height="120" xmlns="http://www.w3.org/2000/svg">
    <foreignObject width="100%" height="100%">
        <div xmlns="http://www.w3.org/1999/xhtml">
            <img alt="Coverage" src="https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/blob/master/.github/badges/task1-md-coverage.svg"/>
        </div>
    </foreignObject>
</svg>

This is a simple online bookstore application built using Spring MVC and Hibernate. The application allows users to perform CRUD operations on books, authors, and genres. It also provides search functionality for books by title, author, or genre.

## Features

- CRUD operations on books, authors, and genres.
- Search books by title, author, or genre.
- Data persistence using Hibernate.
- Built-in H2 database for testing purposes.

## Prerequisites

- Java JDK 11 or higher
- Apache Tomcat 8 or higher

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course.git
```

2. Navigate to the project directory:

```bash
cd bookstore
```

3. Build the project using Gradle or Maven:

```bash
mvn clean package
```

4. Deploy the application to Tomcat:

- Copy the generated WAR file from the `target` (for Maven) directory.
- Paste the WAR file into the `webapps` directory of your Tomcat installation.

5. Start Tomcat:

```bash
# Navigate to the Tomcat bin directory
cd path/to/tomcat/bin

# Start Tomcat
./startup.sh       # On Unix-like systems
startup.bat        # On Windows
```

6. Access the application:

Open a web browser and go to [http://localhost:8080/bookstore/](http://localhost:8080/bookstore/).

## Configuration

- Database configuration can be adjusted in DatabaseConfig class.

## Additional Notes

- This application uses the H2 database by default for testing purposes. For production, consider using a more robust database.
- Make sure to customize and expand the application according to your requirements.

## Available REST endpoints

1. Get all books:
```bash
curl -X GET http://localhost:8080/bookstore/books
```

2. Get a book by ID:
```bash
curl -X GET http://localhost:8080/bookstore/books/1
```

3. Create a new book:
```bash
curl --request POST http://localhost:8080/bookstore/books --header "Content-Type: application/json" \
--data-raw '
{
  "title":"New Book",
  "price":19.99,
  "quantityAvailable":50, 
  "author": {
    "name": "Jane Doe",
    "id": 1
  },
  "genre": {
    "id": 2,
    "name": "Mystery"
  }
}' 
```

4. Update a book by ID:
```bash
curl -X PUT -H "Content-Type: application/json" -d '{"title":"Updated Book","price":15.99,"quantityAvailable":30}' http://localhost:8080/bookstore/books/{bookId}
```

5. Delete a book by ID:
```bash
curl -X DELETE http://localhost:8080/bookstore/books/3
```

6. Search books by title:
```bash
curl -X GET "http://localhost:8080/bookstore/books/search?title=The%20Mystery%20Mansion"
```

7. Search books by author:
```bash
curl -X GET "http://localhost:8080/bookstore/books/search?author=Jane%20Doe"
```

8. Search books by genre:
```bash
curl -X GET "http://localhost:8080/bookstore/books/search?genre=Fiction"
```

## Feedback
**1. Was it easy to complete the task using AI?**

I can't say that I faced with difficulties. ChatGPT simplifies completing the task.

**2. How long did task take you to complete?**

I spent about 3 hours to complete this tasks.

**3. Was the code ready to run after generation? What did you have to change to make it usable?**

I did not change 80-90% of code at all. I tuned configurations and models.

**4. Which specific prompts you learned as a good practice to complete the task?**

Sometimes if you ask something like "... the application", ChatGPT will give you general answer, not related your current application.
"This application" usually can help to clarify ChatGPT your prompt.