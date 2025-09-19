package com.rr.kafka_consumer.repository;

import com.rr.kafka_consumer.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
