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

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Swagger
---

To see your applications Swagger UI `http://localhost:8080/swagger`
