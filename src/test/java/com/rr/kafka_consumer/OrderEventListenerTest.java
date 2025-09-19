package com.rr.kafka_consumer;

import com.rr.kafka_consumer.listener.OrderEvent;
import com.rr.kafka_consumer.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "orders" })
class OrderEventListenerTest {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    @Autowired private OrderRepository repository;

    @Test
    void testConsumeOrderEvent() throws InterruptedException {
        var event = new OrderEvent("ORD002", "PROD789", 3, 149.99, Instant.now());
        kafkaTemplate.send("orders", event.orderId(), event);

        Thread.sleep(2000); // Wait for listener

        assertTrue(repository.findById("ORD002").isPresent());
    }
}