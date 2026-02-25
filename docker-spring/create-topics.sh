#!/bin/sh
echo "Start: Sleep 15 seconds"
sleep 30;
wait;
echo "Begin creating topics"
docker exec Kafka1 kafka-topics --create --if-not-exists --topic yaruss-input --bootstrap-server kafka:9092 --partitions 1 --replication-factor 3
/opt/bitnami/kafka/bin/kafka-topics.sh --create --topic yaruss-input --bootstrap-server kafka:9092 --partitions 1 --replication-factor 3
echo "Done creating topics"
