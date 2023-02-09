FROM maven AS build
WORKDIR /app
COPY . .
RUN mvn package -P prod -X -Dhttps.protocols=TLSv1.2


COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven

RUN find ~/.m2  -name "*.lastUpdated" -exec grep -q "Could not transfer" {} \; -print -exec rm {} \;

RUN mvn install package -P prod -f /usr/src/mymaven && mkdir /usr/src/wars/
RUN find /usr/src/mymaven/ -iname '*.war' -exec cp {} /usr/src/wars/ \;

FROM tomcat:9.0.48-jdk8-openjdk-buster
LABEL Eduardo Oliveira

COPY --from=build /app/target/jsf-primefaces.war /usr/local/tomcat/webapps 

