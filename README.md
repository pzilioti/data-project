# Data Project
This is an api to visualize some data provided in json format. There's 2 entities in this project, *Parent* and *Child*. A *Parent* can have multiple *Child*.

The project was done using Java 11 with Spring boot. The data was loaded in an in-memory H2 database.


## Running

If your system have java and maven installed, just run the following command:

    mvn spring-boot:run


The project will start running in your localhost in port 8080


## Docker alternative

Or, if you're in a Linux environment with docker installed, just run the script deploy.sh

    ./deploy.sh
It'll start a docker instance listening in port 8080


## How to use

The project have 2 endpoints

1. /api/parent?page=0&sort=asc
   
This endpoint needs to receive 2 query params, *page* and *sort*. Since the result is paginated in the backend, the desired page must be informed. Also, the result can be sorted by the parent id, so the *sort* param can receive *asc* or *desc* values.

2. /api/child
   
Returns all the data for the *child* entity.


## Demo

The project is running on my server.

[https://www.zilioti.dev/data_project/api/parent?page=0&sort=asc](https://www.zilioti.dev/data_project/api/parent?page=0&sort=asc)

[https://www.zilioti.dev/data_project/api/child](https://www.zilioti.dev/data_project/api/child)

