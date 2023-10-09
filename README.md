# NFT Marketplace Example Project

This is a sample Kotlin / Gradle / Spring Boot (version 3.1.4) web application that provides API endpoints for CRUD operations with "NFTs" as defined in https://www.kodeco.com/28749494-kotlin-and-spring-boot-getting-started


## How to Run 

* Clone this repository 
* Make sure you are using Azul Zulu JDK 17 and Gradle 8.2.1
* You can build the project and run the application using an IDE like, for example, IntelliJ IDEA

Once the application runs you should see something like this

```
2023-10-09T14:32:53.903+02:00  INFO 1452 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-10-09T14:32:53.909+02:00  INFO 1452 --- [           main] c.r.n.NftmarketplaceApplicationKt        : Started NftmarketplaceApplicationKt in 1.318 seconds (process running for 1.711)
```

## About the Service

The service is just a simple NFT REST service. The data is hardcoded in a mock repository.

Here are some endpoints you can call:

### Get the homepage.

```
http://localhost:8080/homepage
```

### Retrieve all NFTs

```
http://localhost:8080/api

RESPONSE: HTTP 200 (OK)
```

### Retrieve NFT by id

```
http://localhost:8080/api/1

RESPONSE: HTTP 200 (OK)

{
    "id": 1,
    "name": "CryptoPunks",
    "floorPrice": 100.0
}
```

### Create an NFT

```
POST http://localhost:8080/api

{
    "id": 6,
    "name": "Test NFT",
    "floorPrice": 420.69
}

RESPONSE: HTTP 201 (Created)

{
    "id": 6,
    "name": "Test NFT",
    "floorPrice": 420.69
}
```

### Update an NFT

```
PATCH http://localhost:8080/api

{
    "id": 6,
    "name": "Patched NFT",
    "floorPrice": 3.5
}

RESPONSE: HTTP 200 (OK)

{
    "id": 6,
    "name": "Patched NFT",
    "floorPrice": 3.5
}
```

### Delete an NFT

```
DELETE http://localhost:8080/api/1

RESPONSE: HTTP 204 (No Content)
```

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:
