# _Movies API_

### <ins>Goal of the application</ins>

- Movies API to get movie recommendations based on inputted criteria, e.g.
year/genre/actor/ratings/certification. 
- Add checks for the age of who will be watching.

### <ins>Movies API Business Requirements</ins>

- API endpoints with the appropriate HTTP verbs.
- API base URL and endpoints appropriately named.
- Include unit tests.
- Dscriptive README to document the key features of your solution, your assumptions, approaches
and future thoughts.
- API endpoints well documented.
- Error and exception handling considerations included.
- /info endpoint to give the user information on how the application works.
- /health endpoint to give the health of the application (using the Java actuator dependency).

### <ins>User Story</ins>

As a movies API user
I must be able to get movie recommendations based on id/title/year/genre/actor/ratings/certification.

- GIVEN a user wants to request some movie recommendations
- WHEN the request body includes id/title/year/genre/actor/ratings/certification 
- THEN the customer should be given a response object with the movies satisfying the search criteria.

As a movies API registered user
I must be able to get movie recommendations ,based on my age, based on year/genre/actor/ratings/certification.

- GIVEN a user wants to request some movie recommendations
- WHEN the request body includes year/genre/actor/ratings/certification
- THEN the customer should be given a response object with only the movies that can be allowed for my age and 
  satisfying the search criteria.
  

### <ins>Acceptance Criteria and Assumptions</ins>

Movies API should return the movie recommendations based on the search criteria.

Movies API will have two types of users:
    - Unregistered users will be given the movies recommendations without checking their age.
    - Registered users will be given the movies recommendations that are relevant to their age.

Assumptions:

Any user can view the movie recommendations. No authorization checks done.

### <ins>Key Features of the application</ins>

 - Allows registering users using the /users/add endpoint

 - Users can also be viewed and deleted using /users and users/delete/{userId} endpoint

 - Registered users can view their movie recommendations using the /movies/search/{userId} endpoint
   along with the request body (id/title/year/genre/actor/ratings/certification).
   Only age appropriate movies will be given.

 - UnRegistered users can view their movie recommendations using the /movies/search endpoint
  along with the request body (id/title/year/genre/actor/ratings/certification)
   
 - Movies can also be added and deleted using  /movies/add and movies/delete/{movieId}

 - API health can be checked at /health endpoint

 - Information about the API can be found at /info

 - API endpoints can be found at swagger.io


### <ins>Approach to the solution</ins>

- Step One:

As a brainstorming session, we decided on the following:

- scope of the project
- the tools to be used (trello to track, zoom & jamboard for meetings )
- technologies to be used (java, junit 5, maven, postgresSQL )
- standup calls at 10 am everyday

We also created the trello account and added our team to it.

- Step Two:

We designed the database structure and UML class design.

Please Click [here](https://htmlpreview.github.io/?https://github.com/https://github.com/s-prat/movies/blob/master/docs/Postgres_Table_Design.png) to view the database design

We created the project structure.

We made a github repo and pushed the code to git and added contributors to it 
so that we can work on it individually


- Step Three:

We discussed the model to be used including the data type and split up the work to create the movies and user 
related modules for controller, services and repository

We also included the responseUtil so that all the modules return the same structure

- Step Four:

Following the TDD approach, we made the respective modules for movies and users.
The database configuration was also done along with this.

- Step Five:

Once we were able to add/delete/search the movies and users , we moved on to /info and /health.
We included The API documentation through swagger.

- Step Six:

Test all the APIs through Postman. Create a collection in the Postman.

Deployment to AWS using dockers

### <ins> To improve </ins>

- Include a favorite module so that the users can save their favourite movies.

- Use Twilio API to send sms/email

- Authentication to the users

### <ins> Testcases </ins>

Please click [here](https://htmlpreview.github.io/?https://github.com/https://github.com/s-prat/movies/blob/master/docs/Test%20Results%20-%20MoviesAPI.html) to see the test results.

### <ins>Technologies Used</ins>

    Java
    maven
    JUnit 5
    PostgresSQL
    Swagger
    

### <ins>Tools used in the project</ins>

    Trello
    Jamboard
    diagrams.net
    Slack
    Zoom


### <ins>How to run the application and tests</ins>

To run the tests, use the following command:

    mvn test
    