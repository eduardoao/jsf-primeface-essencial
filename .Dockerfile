FROM tomcat:9.0-jre8-alpine

COPY ./target/jsf-primefaces-0.0.2-SNAPSHOT*.war $CATALINA_HOME/webapps/jsf-primefaces-0.0.2-SNAPSHOT.war


