# Reliable Message Reprocessing with Dead Letter Topics

## How to run this sample?

### Start Redpanda

Start a single node Redpanda cluster with Docker Compose by running:

```bash
docker compose up -d
```

### Run Java applications

You can find the Spring Boot Kafka client application inside `spring-kafka-example` directory. Run the main class is `com.redpanda.samples.springkafka.Application`. It will create the `orders` topic in Redpanda upon startup.

### Produce different messages into the `orders` topic

Send a perfect JSON message:

```bash
docker exec -it redpanda rpk topic produce orders
{ "id":2, "amount":65.49}
```

This will log the message payload in the Spring application console.

Send a malformed JSON message:

```bash
docker exec -it redpanda rpk topic produce orders
{ "id":3, "amount":99.99
```

This will be routed to the `orders-dlt` topic, which is the dead letter topic in this scenario.

Simulate a transient error by sending an order with id set to 1. This will be retried in the `orders-retry` topic.

```bash
docker exec -it redpanda rpk topic produce orders
{ "id":1, "amount":12.49}
```
### Cleaning up

```bash
docker compose down
```
