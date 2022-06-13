FROM --platform=linux/amd64 openjdk:17-jdk-alpine
#ARG JAR_FILE=target/document-api.jar
#COPY ${JAR_FILE} document-api.jar
COPY maven/document-api.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]