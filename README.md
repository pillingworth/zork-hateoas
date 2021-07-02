# Zork HATEAOS

## Introduction

A simple demonstrator that allows the world of Zork to be discovered and navigated using a HATEAOS API.

## Prerequisites
The application can de developed and built on Windows, Linux or MacOS.

The application is written in Java and requires Java 8+. AdoptOpenJDK version jdk-11.0.7.10-hotspot was used for development.

The project is built and managed using Maven. Maven version 3.8.1 was used during development.

The project is written in Java and was developed using both Eclipse or IntelliJ IDEs

Recommended Eclipse installation

+ Eclipse 2020-09

Recommended IntelliJ installation:

+ IntelliJ Idea 2020.3
+ Maven Helper plugin
+ Google Java Format plugin

Eclipse and IntelliJ must be configured to use same source code formatting rules to ensure that no changes/diffs are introduced due to IDE reformatting. 

Java code formatting should follow the [Google Java Code style](https://google.github.io/styleguide/javaguide.html) using the [supported tooling](https://github.com/google/google-java-format) if required. The Eclipse the coding style configuration is available here [eclipse-java-google-style.xml](./dev/resources/eclipse-java-google-style.xml)

## How to get started

The project is a single-module maven project.

To build the project and run the unit tests:

```powershell
mvn clean package
```

To build the project and run the unit and integration tests:

```powershell
mvn clean verify
```

To run the application locally

```powershell
java -jar .\target\zoek-hateaos-0.0.1-SNAPSHOT.jar
```

The application will start and expose an endpoint. If run with default settings then this will be accessible on http://localhost:8080/


## Licence

The project is licensed with the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
