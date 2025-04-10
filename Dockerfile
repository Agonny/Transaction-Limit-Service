FROM openjdk:17-alpine

ENV JAR_NAME=Transaction-Limit-Service
ENV JAR_VERSION=1.0.0

CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/$JAR_NAME-$JAR_VERSION.jar /app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar" ]