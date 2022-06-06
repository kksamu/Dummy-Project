FROM amazoncorretto:11-alpine-jdk
EXPOSE 8071
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} inventory-service.jar
ENTRYPOINT ["java","-jar","/inventory-service.jar"]