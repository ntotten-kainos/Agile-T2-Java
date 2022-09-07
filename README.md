# java-dropwizard-swagger-jdbc

Database
---
1. Create an empty database
1. Run the following command from the base of your cloned directory to create the required database structure:
```
mysql --host=<localhost> --user=<your_username> --password=<your_password> <your_database_name> < employeesdb.sql
```

Config
---
1. The following environment variables need to be set to enable database connection:
```
DB_USERNAME
DB_PASSWORD
DB_HOST
DB_NAME
```

How to start the java-dropwizard-swagger-jdbc application
---

1. Run `mvn clean install -DskipTests=true` to build your application
1. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Swagger
---

To see your applications Swagger UI `http://localhost:8080/swagger`

Tests
---

1. Run `mvn clean test` to run unit tests
2. Run `mvn clean integration-test` to run integration tests (this may require VPN for database access)

NOTE: Integration tests require connection to the database which may require VPN

Build and run the service through docker
---

You can build the api in a number of ways using docker and integrate it with a database, these are listed below:
pre-requisite = docker and docker compose are installed in your local system.

1.  Ensure the environment variables are correct for your chosen db or enter these as 
    additional arguments on the docker build command.

    Run "docker build <service name given + optional tag> ." from your src directory.
    This will read from your docker file, build the environment required for the 
    image, build your service and create the image locally.
    
    Use "docker images" to verify your image is available after running the above command.
    
    Now run "docker run -p <chosen port to host locally on your machine>:8080 <your image 
    name given>", this will then spin up your image and host on the given port.
    
2.  You can also deploy by docker compose which handles the network traffic between 
    containers also will deploy a mysql container also.
    pre-requisite = ensure your docker image is available or build with docker compose, ensure 
    that name is in the image name for docker compose.
    
    run "docker compose up" from the src directory, this spins up both the api and a mysql db instance.
    
    Next log onto your db instance and follow the commands under "Database".
    
    Done....the service should be able to operate as expected.      
