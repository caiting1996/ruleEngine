package com.example.ruleengine.message.producer;

import com.example.ruleengine.message.config.KafkaProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public abstract class AbstractKafkaProducer {
    private KafkaProducer<String, String> producer=null;
    public AbstractKafkaProducer(KafkaProducerConfig kafkaProducerConfig){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProducerConfig.getHost()+":"+kafkaProducerConfig.getPort());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,kafkaProducerConfig.getKeySerialize());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,kafkaProducerConfig.getValueSerialize());
        this.producer=new KafkaProducer<String, String>(props);
    }

    public void producer(String topic,String key,String message){
        producer.send(new ProducerRecord<String, String>(topic, key,message));
    }
}
