FROM eclipse-temurin:17.0.5_8-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY /target/*.jar accrual-app.jar

CMD ["java", "-jar", "accrual-app.jar"]