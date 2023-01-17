# API and Gateway monitoring using Prometheus and Grafana 
This repository contains a docker compose file that when run will create a micro service that is connected to a postgres database and will be accessible through a Kong gateway. After following the steps below the API and the Kong gateway will be monoratbale through Grafana 

* To deploy the application clone this repository into the directory of your choosing.
    - Before building the appicalion open the docker compose file and change the username and password in the db environment section to a username and password of your choice. Also update the username, password, and source url in the server enviroment to match those of the db enviroments. 
    - Comments for where to make these changes can be found in the docker-compose.yml file. 
* Ensure that Docker desktop is running on your device. 
* In CMD run the command `docker-compose up --build`, this will take around 4 minutes to run.
* To see that all containers are running in the Docker desktop window navigate to the containers section. The running containers should include:
    - hello-world
        * The container that builds and hosts the API
    - kong: with ports 8000, 8001, 8002, 8433, and 8444 exposed.
        * the container that hosts the Kong gateway, having the manager on port 8002.
    - kong-database
        * The container hosting the database that stores the configuration of services and routes for the kong gateway.
    - db
        * The container that hosts the postgres database for our API, where users are stored in the users_table.
    - grafana 
        * The container that hosts the Grafana monitoring service.

### Setting up API services and routes.
* In a new CMD window cd into the curl folder and run the `docker-compose up --build` command.
* If the container exits with code 0 the services and routes have been created successfully.
* To further test this, we can go to `localhost:8002` to access the kong gateway manager here we could see 5 services each with their own route. 

### Setting up Kong Prometheus plugin
* While still at the address `loaclhost:8002` underneathe sevices you will find plugins.
* Select "install new plugins" and in the search field labeled "filter plugins" look up `prometheus`.
* Select Enable, select Global, and check the 5 config tags at the bottom of the page
* Select Install.

### Setting up Prometheus and Grafana.
* Look up `localhost:3000`, this will direct you to the Grafana page, login using username admin and password admin.
* Once you have set a new password you will be directed to the home page where you will need to make a new datasource.
* Click Add your first data source, and in the time series data section select prometheus.
* Give the data source a sutible name of your choising, and the URL `http://prometheus:9090`
* All other fields can be left blank, now scoll to the bottom of the page and select save and test.
* A pop up should appare saying the data source is working. Now hover your mouse over the 4th item from the top on the right hand side nav bar (this should show dashboard options) and select import.
* Upload the `basic-api-dashboard.json` file located in the prometheus folder of this repository, then select the data source you create in the previous steps.
* Click Import. You will be directed to a dashboard page with 3 panels, one with successful http requests, one with bad http requests, and one with the CPU usage of the API. 
* Save the dashboard and return to it once you hav esent some test data using postman in the last steps.



### Cleaning up docker containers
* To remove now unused docker container (this being the curl and kong bootstrap containers) we can use the command `docker container prune -f` to remove them.

### Confirming the database is set up correctly.
* In CMD run the command `docker exec -it db psql -U {USERNAME YOU SET IN FIRST STEP}`.
* This will put you inside a terminal of the db container and run the psql command line tool. 
* Run the `SELECT * FROM users_table;` query, the output should be a 5 column by 0 row table with all attributes of the user model as the headers for the columns.
* Exit the psql terminal by typing `\q` and pressing enter.

### Populate the base.
* Run the command `docker exec -it db psql -U {USERNAME SET IN FIRST STEP} -d {USERNAME SET IN FIRST STEP} -f /tmp/init.sql`
* This command populates the database with 1000 unique rows each representing a different user.

### Testing in Postman
* Import the JSON file named `microservice-on-kong-tests.postman_collection.json` into Postman 
* Run the GetAllUsers request, the response should be a list of 1000 users in JSON format, with a status code of 200 OK.
* Run the AddNewUser request, there should be no response, and a status code of 201 CREATED.
* Run the UpdateUserById request using an id in the range of 1-1000 and add the updated information to the raw JSON body of the request, this should only return a 200 OK status code  
* Run the GetUser request with the id chosen in the previous step. we should see the updated user and also a 200 OK status code.  
* Run the DeleteUserByID request with the id of the user you just updated in the value column in path variables, there should be no response and a status code of 204 NO CONTENT.
* To confirm the user has been deleted run the GetUser request with the same id, we should get a 500 internal server error, and at the bottom of the error should be the message `user not found`.
* To see metrics data return back to `localhost:3000` and return to the dashboard you had previously set up. 


