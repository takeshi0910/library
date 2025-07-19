FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY . /app

RUN chmod +x gradlew
RUN ./gradlew build --stacktrace --info --no-daemon

CMD ["java", "-jar", "build/libs/library-0.0.1-SNAPSHOT.jar"]