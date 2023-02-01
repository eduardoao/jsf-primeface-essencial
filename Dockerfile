FROM java:8
FROM maven:alpine as builder

COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven

RUN mvn clean install -f /usr/src/mymaven && mkdir /usr/src/wars/
RUN find /usr/src/mymaven/ -iname '*.war' -exec cp {} /usr/src/wars/ \;

FROM tomcat:9.0.48-jdk8-openjdk-buster
COPY --from=builder /usr/src/wars/* /usr/local/tomcat/webapps/