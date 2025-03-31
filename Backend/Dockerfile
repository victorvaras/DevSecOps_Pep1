FROM openjdk:17
ARG JAR_FILE=target/tingeso-backend.jar
COPY ${JAR_FILE} tingeso-backend.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/tingeso-backend.jar"]