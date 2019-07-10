# Itaú Challenge
[![Build Status](https://travis-ci.org/juniormazella/tasks.svg?branch=master)](https://travis-ci.org/juniormazella/tasks) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=juniormazella_tasks&metric=alert_status)](https://sonarcloud.io/dashboard?id=juniormazella_tasks) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=juniormazella_tasks&metric=coverage)](https://sonarcloud.io/dashboard?id=juniormazella_tasks)

# Pre requirements

    Maven v3.5+
    Jdk v1.8.0_211+
    Docker v18.09.2+
    Docker-compose v1.24.0

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

# Solution Architecture
![Proposed solution architecture](https://github.com/juniormazella/tasks/blob/master/solution-architecture.png)

Basically a very simple solution architecture was designed with the tools present.
It was defined a fixed number of nodes thus impossible to auto scaling in this case, this is necessary because of the strategy of balancing the requests, to make this architecture auto scaling, there is a need to change the load balancer to another implementation, for example , Kong (https://konghq.com/kong/) or Istio (https://istio.io).

A logging system was fully implemented using RabbitMq, Logstash, Elasticsearch and Kibana. For this to happen a "log appender" has been created in the application where every log event is published in a topic, which reaches a queue, thus enabling Logstash to receive this message, to process and record in Elasticsearch, within Kibana it is possible to view all and any log event generated on any of the application nodes.

For balancing the requests Nginx was used, which is not the best solution but the easiest and fastest in this context, there is a configuration file indicating which nodes are available for routing the requests.

As a database solution was used Postgres, a database that grew the use in corporate environments.

# Steps to Build and Run
At root directory of the application run the command:
    
    mvn clean package docker:build
   
This command will build, package, and generate the generated JAR docker image.
After that it is necessary to mount our environment docker so that it is possible to run the server, for this go to the folder:

    src/docker
    
To build the custom Logstash image that was generated, run the command:

    docker-compose build
    
After the Logstash image has been created, it is necessary to download the remaining images, so run the command:

    docker-compose pull
    
Note that some items are marked as error, no problem, because these images are managed by our context.
To start the application and all its dependencies run the command:

    docker-compose up
    
This command will provide 4 nodes of the main application, the nodes are named after: thor, superman, spiderman, hal-jordan. 
During the process it is normal to show an error when trying to connect to RabbitMq, because it is one of the last dependencies to become available, but in a few seconds it will become available.


After all the services are available you can see the load balancing running through the url below, after some reload of the screen you will see that the request falls on another node.

    http://localhost:5555/actuator/info

You can check the Prometheus status at:

    http://localhost:9090/targets

You can access logs from application at:

    http://localhost:5601


Obs. you will need to create a index with name:

    logstash-*
    
You can configure the dashboards from Prometheus at Grafana, for do it access url:

    http://localhost:3000
    Username: admin
    Password: admin
    
To setup a micrometer dashboard you can checkout at:

    https://grafana.com/dashboards/4701
    
Obs. 
to configure the micrometer control panel, you first need to add a Prometheus datasource with the address of 'http://prometheus:9090', the Docker network will resolve the hostname

We will see below some commands to test the API:
    
- To insert a Task:

    
    curl -d '{"description":"teste", "points": 5}' -H "Content-Type: application/json" -X POST http://localhost:5555/tasks
   
- To change status of Task:

   
    curl -d '{"status" : "COMPLETED", "description":"teste", "points": 5}' -H "Content-Type: application/json" -X PUT http://localhost:5555/tasks/1
    

- To list all Tasks

       
    curl http://localhost:5555/tasks
    

- To get specific Task

    
    curl http://localhost:5555/tasks/1
    
    
- To delete a Task


    curl -X DELETE http://localhost:5555/tasks/1

