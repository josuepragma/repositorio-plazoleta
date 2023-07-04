FROM openjdk:11
EXPOSE 8092
ADD /build/libs/small-square-microservice-1.0.jar small-square-microservice-1.0.jar
ENTRYPOINT ["java", "-jar", "/small-square-microservice-1.0.jar"]