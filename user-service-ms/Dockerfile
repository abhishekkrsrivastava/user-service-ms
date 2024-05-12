FROM openjdk:jdk
WORKDIR /appcontainer
EXPOSE 9196
COPY ./target/user-service-ms.jar /appcontainer
COPY ./src/td.txt /appcontainer
ADD ./target/user-service-ms.jar user-service-ms.jar
ENTRYPOINT ["java","-jar","user-service-ms.jar"]