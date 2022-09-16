FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app-0.0.1.jar
ENTRYPOINT ["java","-jar","/app-0.0.1.jar"]