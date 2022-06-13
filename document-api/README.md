# Document API

## Build & Run

Build and push to docker

```bash
mvn clean install
```

Build Docker image on Mac OS. For Macs with M1+ we have to add the plattform linux/amd64

```bash
docker build --platform=linux/amd64 -t document-api:0.0.1 .
```  

### RUN

After the docker iamge was build and push to the local Docker registry, we can run the Document API
on the local Docker Daemon. Here we expose the port 8081 not the standard port 8080.x^

```bash
docker run -d \
--name document-api \
--platform linux/amd64 \
-p 9080:8080 \
tastaturathlet/document-api
```

## Spring Boot Actuator

```bash
http://localhost:9080/tastaturathlet/actuator/info
```

### JDK 17 Alpine

```bash
docker pull --platform=linux/amd64 openjdk:17-alpine
```
