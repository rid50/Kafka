# version: '3.8'
networks:
  kafka-net:
    driver: bridge
    
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_KAFKA_BOOTSTRAP_SERVERS: localhost:9092
    networks:
      - kafka-net

volumes:
  kafka_data:
    driver: local
    

