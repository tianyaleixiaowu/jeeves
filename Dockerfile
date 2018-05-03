FROM daocloud.io/brave8/maven-jdk8

ADD jeeves-0.2.0.jar /tmp/app.jar
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java","-jar","/tmp/app.jar"]