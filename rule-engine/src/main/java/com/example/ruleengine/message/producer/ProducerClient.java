package com.example.ruleengine.message.producer;

import com.example.ruleengine.message.config.KafkaProducerConfig;

public class ProducerClient extends AbstractKafkaProducer {


    public ProducerClient(KafkaProducerConfig kafkaProducerConfig) {
        super(kafkaProducerConfig);
    }
}
