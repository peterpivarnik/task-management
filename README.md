### Run instructions

Below is an example of how you can run this demo.

 0. Prerequisities:
  make sure you have installed git, maven and java 21 on your machine
 1. Navigate to the project root folder
 2. Build project
   ```sh
   mvn clean install
  ```
3. Run application
    Run by running main method in TaskManagementApplication
4. Check in browser if application is correctly started in docker
       ```
    localhost:8080/actuator/health
       ```
5. Test application by sending requests from postman or from resource test.http


Assignment:
Build a Task Management microservice with the following features:
1. Domain Model
  * User (e.g. id, username, fullName)
  * Tasks (createdAt, name), with two distinct categories:
    a) Bug (e.g. severity, stepsToReproduce)
    b) Feature (e.g. businessValue, deadline)
  * You can name these categories/fields however you wish, but the important aspect is that they are represented differently in the domain model.
  * A User can have many Tasks.
  * Store your data in a PostgreSQL database.
2. REST Endpoints
Create standard CRUD (Create, Read, Update, Delete) operations for both User and Task. For tasks, you must handle or account for the different categories (Bug, Feature) and their specific attributes.
  * User
    - Create a user
    - Get a user by id
    - List all users
    - Update a user (e.g., fullName)
    - Delete a user
  * Task
     - Create a task (assign to a user). Must handle the distinct fields based on the category (e.g., severity, deadline, etc.).
     - Get a task by id
     - List all tasks
     - Update a task (including category-specific fields)
     - Delete a task
3. Filtering & Searching
  * Provide a way to filter tasks by:
    - status (e.g. OPEN, IN_PROGRESS, DONE)
    - userId
  * (Optional, if time permits) implement text search in task titles/descriptions.
3. Migrations
  * Use Flyway or Liquibase to manage database schema changes.
  * Create separate migration scripts for User and Task (including the two categories of tasks).
4. Error Handling
  * Return proper HTTP status codes (e.g., 404 for not found, 400 for bad request).
  * Provide meaningful error messages in JSON when requests fail.
5. Testing
  * Use JUnit (or another framework) to create at least some unit tests.
  * If time permits, create at least one integration test that checks end-to-end functionality (e.g., using MockMVC or Testcontainers).
6. Documentation
  * A README.md that includes:
    - How to build, run, and test the application
    - A brief explanation of your design and approach
