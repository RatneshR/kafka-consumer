package com.rr.kafka_consumer.config;

import com.rr.kafka_consumer.listener.OrderEventListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.rr.kafka_consumer.listener.OrderEvent;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, OrderEvent> consumerFactory() {
        var deserilizer = new JsonDeserializer<>(OrderEvent.class);
        deserilizer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                        ConsumerConfig.GROUP_ID_CONFIG, "order-consumers",
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserilizer
                ),
                new StringDeserializer(),
                deserilizer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderEvent> orderKafkaListnerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, OrderEvent>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
