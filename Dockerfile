FROM maven AS build
WORKDIR /app
COPY . .
RUN mvn package -P prod -Dhttps.protocols=TLSv1.2


FROM tomcat:9.0.48-jdk8-openjdk-buster
LABEL Eduardo Oliveira

COPY --from=build /app/target/jsf-primefaces.war /usr/local/tomcat/webapps 

