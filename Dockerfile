FROM adoptopenjdk/openjdk18

ARG JAR_FILE=target/clinic-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080
