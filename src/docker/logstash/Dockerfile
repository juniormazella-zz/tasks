FROM docker.elastic.co/logstash/logstash:6.4.3
MAINTAINER José Carlos Mazella Junior <jose.mazella@mult-e.com.br>

RUN logstash-plugin install logstash-input-rabbitmq

COPY ./config/logstash.yml /usr/share/logstash/config/logstash.yml
COPY ./pipeline/logstash.conf /usr/share/logstash/pipeline
