#!/bin/sh
/opt/bitnami/kafka/bin/kafka-topics.sh --create --topic yaruss-input --bootstrap-server kafka:9092
echo "topic yaruss-input was create"