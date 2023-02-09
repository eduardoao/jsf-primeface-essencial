# Use an official Java runtime as the base image
FROM openjdk:8-jdk-alpine as build

# Copy the built artifact from the Maven build to the Docker image
COPY target/jsf-primefaces.war /app/jsf-primefaces.war

# Set the working directory
WORKDIR /app


FROM tomcat:9.0.48-jdk8-openjdk-buster
LABEL Eduardo Oliveira

COPY --from=build /app/target/jsf-primefaces.war /usr/local/tomcat/webapps 

