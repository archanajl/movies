FROM adoptopenjdk:11-jre-hotspot
RUN addgroup -system spring && adduser --system springuser -ingroup spring
USER springuser:spring
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]