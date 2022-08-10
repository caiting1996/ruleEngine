package com.example.ruleengine.config;

import akka.actor.ActorSystem;
import com.example.ruleengine.message.config.KafkaConsumerConfig;
import com.example.ruleengine.message.config.KafkaProducerConfig;
import com.example.ruleengine.message.consumer.ConsumerClient;
import com.example.ruleengine.message.producer.ProducerClient;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanConfig {
    @Autowired
    KafkaConfig kafkaConfig;
    @Autowired
    AkkaConfig akkaConfig;
    @Bean
    public ActorSystem actorSystem() {
        return ActorSystem.create(akkaConfig.actorSystem_name);
    }
    @Bean
    public NashornScriptEngineFactory factory() {
        return new NashornScriptEngineFactory();
    }

    @Lazy
    @Bean
    public ConsumerClient consumerClient(){
        KafkaConsumerConfig kafkaConsumerConfig=new KafkaConsumerConfig(kafkaConfig.getHost(),kafkaConfig.getPort(),kafkaConfig.getGroup_id(), StringDeserializer.class, StringDeserializer.class);
        return new ConsumerClient(kafkaConsumerConfig);
    }

    @Lazy
    @Bean
    public ProducerClient producerClient() {
        KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig(kafkaConfig.getHost(), kafkaConfig.getPort(), StringSerializer.class, StringSerializer.class);
        return new ProducerClient(kafkaProducerConfig);
    }

}
