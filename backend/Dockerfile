FROM --platform=linux/x86_64 openjdk:18-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR app
ARG JAR_FILE=build/libs/app-0.0.1.jar
COPY --chown=spring:spring ${JAR_FILE} ./
WORKDIR storage/images
COPY --chown=spring:spring storage/images ./
WORKDIR /app
ENTRYPOINT ["java","-jar","./app-0.0.1.jar"]