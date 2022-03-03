FROM maven:latest
#set maven dependency to specific version?
WORKDIR /code

COPY . /code

RUN mvn clean install

ENV DB_USERNAME = root, DB_PASSWORD = example, DB_HOST = mysql, DB_NAME = localdb

EXPOSE 8080

CMD ["java","-jar", "/code/target/JavaWebService-1.0-SNAPSHOT.jar", "server", "/code/config.yml"]