# Build stage
FROM maven:3-jdk-11 as build
RUN curl -sL https://deb.nodesource.com/setup_12.x | bash -
RUN apt-get update -qq && apt-get install -qq --no-install-recommends nodejs

WORKDIR /usr/src/app/
COPY src src
COPY frontend frontend
COPY pom.xml .
RUN mvn clean package -DskipTests -Pproduction

# Run stage
FROM openjdk:11
COPY --from=build /usr/src/app/target/vaadin-crm-2.0-SNAPSHOT.jar /usr/app/app.jar
RUN useradd -m myuser
USER myuser
EXPOSE 5000
CMD java -jar -Dspring.profiles.active=prod /usr/app/app.jar
----
