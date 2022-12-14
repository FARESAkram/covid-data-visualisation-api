FROM openjdk:17-alpine

COPY . .

RUN ./mvnw clean package -DskipTests=true

CMD java -jar ./target/covid-data-visiualisation-0.0.1-SNAPSHOT.jar