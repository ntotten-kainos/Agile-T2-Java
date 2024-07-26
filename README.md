# Agile - Team 2 - Backend API

Database
---
1. Using a tool such as MySQLWorkbench or DBeaver:
2. Create a new test database locally. E.g. `CREATE DATABASE JobPortal_Test_<your username>`.
3. Make note of your test db name as this will be needed to point your dev environment with `DB_NAME`.
4. Select this database with `USE JobPortal_Test_<your username>`.


Config
---
1. The following environment variables need to be set to enable database connection:
```
DB_USERNAME=<your username>
DB_PASSWORD=<your password>
DB_HOST=academy2020.cpc8rvmbbd9k.eu-west-2.rds.amazonaws.com
DB_NAME=JobPortal_Test_<your username>
```

Database Migrations
---
1. Add your SQL script to `resources.db.migration` directory
2. Add the following lines to your ~/.zshrc file:

```
export FLYWAY_URL="jdbc:mysql://YOUR_DB_HOST/YOUR_DB_NAME"
export FLYWAY_USER="YOUR_DB_USERNAME"
export FLYWAY_PASSWORD="YOUR_DB_PASSWORD"
export FLYWAY_BASELINE_ON_MIGRATE=true
```

3. Reload your terminal session if required:

```
. ~/.zshrc
```

4. Run Flyway command through Maven:

```
mvn flyway:migrate
```

Database Migration - Production
Swagger
---

To see your applications Swagger UI `http://localhost:8080/swagger`

Database Migration - Production
---

1. Add following secrets to your GitHub repo:
```
DB_USERNAME - the prod db username
DB_PASSWORD - the prod db password
DB_HOST - the prod db host
DB_NAME - the prod db password
```

2. Raise a pull request with your script in the `resources.db.migration` directory
3. After approvals, merge pull request; this will trigger the migration action to run in Github
4. Ensure migration successfully runs against prod database


How to start the application
---

1. Run `mvn clean install -DskipTests=true` to build your application
1. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

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
