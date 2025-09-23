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


