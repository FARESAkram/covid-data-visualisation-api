FROM openjdk:17-alpine

COPY . .

RUN ./mvnw clean package -DskipTests=true

EXPOSE 80

ENTRYPOINT ["java", "-jar", "./target/covid-data-visiualisation-0.0.1-SNAPSHOT.jar", "--server.port=80"]