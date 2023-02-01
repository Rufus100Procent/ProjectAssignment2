FROM tomcat:11.0.0-jdk17    
WORKDIR /usr/local/tomcat/webapps
RUN apt-get update -y
RUN apt-get -y install curl
CMD ["catalina.sh", "run"]

