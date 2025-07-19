FROM openjdk:17-jdk-slim

WORKDIR /app
COPY . /app

RUN chmod +x gradlew
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/library-0.0.1-SNAPSHOT.jar"]