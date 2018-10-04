FROM ubuntu:trusty
LABEL maintainer martinmore52@hotmail.com

RUN apt-get update && \
    apt-get install -y software-properties-common python-software-properties
RUN add-apt-repository ppa:openjdk-r/ppa -y && \
    apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    java -version
   

EXPOSE 8080
VOLUME /tmp
#  add is based at: target/docker/gs-rest-services/build
ADD demo-spring-docker-log-0.0.1-SNAPSHOT.jar demo-spring-docker-log.jar
RUN bash -c 'touch /demo-spring-docker-log.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo-spring-docker-log.jar"]
