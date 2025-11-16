# User Microservice
## Monolith decomposition into microservices
This project is part of the Graduation Project in Computer Science (PGC) at UFABC.
The work is a case study on decomposing a monolithic application into a microservices architecture.

As the practical outcome, the project delivers a prototype of the User microservice, 
which represents one of the decomposed domains from the original monolithic application.

## Project Setup
**This project uses:**\
Java `17.0.6-tem` \
Docker `4.46.0`

### Sdkman
The service was done using a MacOs system. 
To select Java version, `sdkman` was used. \
[Sdkman installation guide](https://sdkman.io/install/) 

After installing sdkman, install java version:
```
sdk install java 17.0.16-tem
```

To use the version in .sdkmanrc:
```
sdk env
```

### Docker

[Docker installation guide](https://docs.docker.com/get-started/)

Start docker containers:
```
docker-compose up
```

## Running

To build the application:
```
./gradlew clean build
```

To run the tests and check the statuses:
```
./gradlew clean test
```

To run the api locally (build the application first):
```
./gradlew bootRun
```
## Monitoring

### Swagger
Swagger is an interactive API documentation tool that allows developers to visualize and test endpoints directly from the browser.
It is automatically generated based on your application’s OpenAPI (Swagger) annotations.

Once the application is running, open the Swagger UI in your browser. 
From the UI you can:
- See all available endpoints
- Inspect request/response schemas
- Execute API calls directly
- Validate payloads

**Swagger**: http://localhost:8080/swagger-ui/index.html \
**OpenAPI Json**: http://localhost:8080/v3/api-docs

![alt text](/img/swagger.png)

### Prometheus

Prometheus is a monitoring and alerting system that collects numerical metrics from your application.
Your service exposes metrics through a `/actuator/prometheus` endpoint (Spring Boot) or another configured metrics endpoint. 

Prometheus periodically scrapes the application to retrieve metric data such as HTTP Requests Count.

**Prometheus UI**: http://localhost:9090

![alt text](/img/prometheus.png)

### Grafana

Grafana is a visualization platform that lets you build dashboards using Prometheus, Loki, and other data sources.

**Grafana UI**: http://localhost:3000

![alt text](/img/grafana.png)

### Logging with Loki (Grafana logs)

Loki is a log aggregation system optimized for labels and querying.
The application writes logs to a file (`app/logs/app.log`), and the Loki Agent / Promtail sends these logs to Loki.

With Loki connected to Grafana, you can query logs using LogQL.

![alt text](/img/loki.png)
