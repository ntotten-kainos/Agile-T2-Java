FROM maven:latest
#set maven dependency to specific version?
WORKDIR /code

COPY . /code

RUN mvn clean install

#ENV -- set env var DB_USERNAME,DB_PASSWORD,DB_HOST,DB_NAME and reference git secrets when setup

EXPOSE 8080
EXPOSE 3306

CMD ["java","-jar", "/code/target/JavaWebService-1.0-SNAPSHOT.jar", "server", "/code/config.yml"]