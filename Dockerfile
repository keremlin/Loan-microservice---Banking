FROM openjdk:latest
COPY target/loan-0.0.1-SNAPSHOT.jar loan.jar
ENTRYPOINT [ "java","-jar", "/loan.jar" ]
