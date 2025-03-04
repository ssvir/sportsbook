To run this project you need to have docker

Run `docker-compose up` command to run all services and mongoDB in different containers - `not finish with this one, services can't connect to mongo`

You can run locally by using maven or IDEA, `first` up mongoDB and then one by one services - better to use this one
## API Endpoints

### Data Storing Service

* `POST /api/v1/sport-events`: Create a new Sporting Event
* `PUT /api/v1/sport-events`: Update an existing Sporting Event
* `DELETE /api/v1/sport-events/{id}`: Delete a Sporting Event

### Data Retrieving Service

* `GET /api/v1/sport-events`: Retrieve a list of non-settled Sporting Events, filtered by Sport and sorted by Start Time as param take Sport name
* `GET /api/v1/sport-events/{id}`: Retrieve a single Sporting Event by ID

### Useful Actuator Endpoints
### Endpoint	Description
* `/actuator`	        Lists all available endpoints
* `/actuator/health`	Shows application health status
* `/actuator/info`	    Custom application info (version, name, etc.)
* `/actuator/metrics`	Displays various application metrics
* `/actuator/loggers`	Shows and modifies log levels
* `/actuator/env`	    Displays environment properties

## Improvements
Separate common code to another project, add Swagger