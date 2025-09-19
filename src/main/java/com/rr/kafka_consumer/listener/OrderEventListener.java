package com.rr.kafka_consumer.listener;

import com.rr.kafka_consumer.model.OrderEntity;
import com.rr.kafka_consumer.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    private final OrderRepository repository;

    public OrderEventListener(OrderRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "orders", groupId = "order-consumers", containerFactory = "orderKafkaListenerContainerFactory")
    public void listen(OrderEvent event) {
        var entity = new OrderEntity(
                event.orderId(),
                event.productId(),
                event.quantity(),
                event.price(),
                event.timestamp()
        );
        repository.save(entity);
    }
}
