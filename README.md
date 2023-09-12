# ParentTecherConferences

The Conferences file contains the backend, Using Java Spring Boot, it connects to a MongoDB database that stores the student, teacher, and timing info
    Current Completion:
        - created REST API endpoints
        - retrieve students by the given Name
        - Generate an appointment object and store it in the database 
        - Delete an appointment from the database 
        
The ConfrenceClient contains the front end, mainly using react.js, 
    Current Completion:
        - Home page prompts the user to enter the student name and calls REST API to get the info 
        - navigates to the scheduling page if the student is found, the user is presented with timing and availability for conferences
        - The user is prompted to enter the timing and check if the timing and date are available
        - POST request to create the appointment in the database
