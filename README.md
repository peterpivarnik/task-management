### Run instructions

Below is an example of how you can run this demo.

 0. Prerequisities:
  make sure you have installed git, maven, docker and java 21 on your machine
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
