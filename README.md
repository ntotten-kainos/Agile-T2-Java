# WebService

Database
---
1. Run the following command from the base of your cloned directory to create the required database structure:
```
mysql --host=<localhost> --user=<your_username> --password=<your_password>  -e "employeesdb.sql"
```

Properties file
---
1. Create a file in the base of your cloned directory called 'employeesdb.properties'
1. Add the following values to the file:
```
user: <username>
password: <password>
host: <database host>
database: <database name>
```

How to start the java-dropwizard-swagger-jdbc application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Swagger
---

To see your applications Swagger UI `http://localhost:8080/swagger`
