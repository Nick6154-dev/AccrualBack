FROM eclipse-temurin:17.0.5_8-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY /target/*.jar devengamiento-app.jar

CMD ["java", "-jar", "devengamiento-app.jar"]