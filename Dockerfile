FROM openjdk:jdk
WORKDIR /appcontainer
EXPOSE 9195
COPY ./target/user-service-ms.jar /appcontainer
ADD ./target/user-service-ms.jar user-service-ms.jar
ENTRYPOINT ["java","-jar","user-service-ms.jar"]