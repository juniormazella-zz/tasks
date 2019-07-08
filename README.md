# Itaú Challenge
[![Build Status](https://travis-ci.org/juniormazella/tasks.svg?branch=master)](https://travis-ci.org/juniormazella/tasks) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=juniormazella_tasks&metric=alert_status)](https://sonarcloud.io/dashboard?id=juniormazella_tasks) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=juniormazella_tasks&metric=coverage)](https://sonarcloud.io/dashboard?id=juniormazella_tasks)

# Pre requirements

    Maven v3.5+
    Jdk v1.8.0_211+
    Docker version v18.09.2+

# Technologies Involved

    Maven
    Spring Boot 2
    Spring Data
    Liquibase
    JUnit
    DBUnit
    Postgres
    Docker
    Prometheus
    Grafana

# Requirements
    
Create a rest api that stores and updates "tasks" (GET, POST, PUT, DELETE).

Details:
    
    

# About solution

# How to build and run tests of project

    mvn clean package
    
# How to build and generate the docker image of application

    mvn clean package docker:build
    
# How to construct in local cloud environment
    