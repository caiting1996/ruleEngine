package com.example.ruleengine.message.consumer;


import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import com.example.ruleengine.actor.ActorSystemContext;
import com.example.ruleengine.message.config.KafkaConsumerConfig;

import com.example.ruleengine.utils.JsonUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;


public class ConsumerClient extends AbstractKafkaConsumer {
    @Autowired
    private ActorSystemContext actorSystemContext;
    private static final String APP="AppActor";
    public ConsumerClient(KafkaConsumerConfig kafkaConsumerConfig) {
        super(kafkaConsumerConfig);
    }

    @Override
    public void dealMessage(ConsumerRecord<String, String> record) throws Exception {
        System.out.println("收到消息"+record);
        Map msg= JsonUtil.string2Obj(record.value(),Map.class);
        ActorSelection selection=actorSystemContext.getActorSystem().actorSelection(actorSystemContext.getAkkaConfig().getActor_path()+APP);
        selection.tell(msg, ActorRef.noSender());

        }
    }

