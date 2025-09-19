package com.rr.kafka_consumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.Instant;

@Entity
public class OrderEntity {
    @Id
    private String orderId;
    private String productId;
    private int quantity;
    private double price;
    private Instant timeStamp;

    public OrderEntity() {}

    public OrderEntity(String orderId, String productId, int quantity, double prioe, Instant timeStamp) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = prioe;
        this.timeStamp = timeStamp;
    }

    // Getters and setters omitted for brevity
}
