FROM --platform=linux/amd64 amazoncorretto:11-alpine3.14-jdk
RUN addgroup -S spring && adduser -S springuser -G spring
USER springuser:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]