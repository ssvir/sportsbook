To run this project you need to have docker

Run `docker-compose up` command to run all services and mongoDB in different containers 

You can run locally by using maven or IDEA, `first` up mongoDB and then one by one services
## API Endpoints

### Data Storing Service

* `POST /api/v1/sport-events`: Create a new Sporting Event
* `PUT /api/v1/sport-events`: Update an existing Sporting Event
* `DELETE /api/v1/sport-events/{id}`: Delete a Sporting Event

### Data Retrieving Service

* `GET /api/v1/sport-events`: Retrieve a list of non-settled Sporting Events, filtered by Sport and sorted by Start Time as param take Sport name
* `GET /api/v1/sport-events/{id}`: Retrieve a single Sporting Event by ID

## Improvements
Separate common code to another project, add Swagger