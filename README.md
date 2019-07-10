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

# About