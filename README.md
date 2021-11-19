# PetStore Application
### SCS3203 / IS3108 Middleware Architecture - JavaEE Assignment with a mini project
### P.D.R.Thathsaranie - 18001701


## Introduction
Pet-Store API which will be consumed by the front-end developers for developing the Pet-Store web-application.
MicroProfile Starter has generated this application and this project uses Quarkus( https://quarkus.io/ ), the Supersonic Subatomic Java Framework.


### The functionalities

- Adding/View/Update/Delete pets-types through the API
- Adding/View/Update/Delete pets through the API
- Search for pets by name, age...etc through the API


## Packaging and running the application

To build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build
    
> **_NOTE:_** In Windows machine use 

    gradlew.bat build

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open browser at the following URL

    http://localhost:8080/index.html

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

    ./gradlew quarkusDev

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Run Test Suite

Run the following command on terminal 
    ./gradlew test

> **_NOTE:_** Test suit also can be directly run using intellij idea 

## Creating a native executable

GraalVM can be used to create the native image.

You can create a native executable using:

    ./gradlew build -Dquarkus.package.type=native

Or can run the native executable build in a container using:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

Or to use Mandrel distribution:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:20.3-java11

To execute the native executable:

    ./build/petstore-runner


## Deploy Application with Docker-Compose

To create a Docker image

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true

Go to deploy floder and run

    docker-compose up -d

Open “Quarkus Microprofile Metrics” dashboard

    http://localhost:3000/dashboards
