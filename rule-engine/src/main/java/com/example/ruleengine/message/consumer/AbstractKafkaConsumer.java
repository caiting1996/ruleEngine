package com.example.ruleengine.message.consumer;

import com.example.ruleengine.message.config.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;


public abstract class AbstractKafkaConsumer {

    private boolean autoCommit = true;
    private volatile boolean isRunning = true;
    private volatile boolean updateTopic = false;
    private volatile ArrayList<String> topics = new ArrayList<>();

    private KafkaConsumer<String, String> consumer = null;

    public AbstractKafkaConsumer(KafkaConsumerConfig kafkaConsumerConfig) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerConfig.getHost() + ":" + kafkaConsumerConfig.getPort());
        // 消费分组名
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerConfig.getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfig.getKeyDeserialize());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfig.getValueDeserialize());
        this.consumer = new KafkaConsumer<>(props);
    }

    public AbstractKafkaConsumer(Properties props) {
        if (props.containsKey(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG)) {
            autoCommit = Boolean.valueOf(props.getProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG));
        }
        this.consumer = new KafkaConsumer<>(props);
    }


    public void init() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!topics.isEmpty()) {
                consumer.subscribe(topics);
                break;
            }
        }
        //消费消息
        consumer();
    }

    public void consumer() {
        try {
            while (isRunning) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        dealMessage(record);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
                //是否自动提交
                if (!autoCommit) {
                    consumer.commitSync();
                }
                //判断是否需要重新订阅
                if (updateTopic) {
                    consumer.subscribe(topics);
                    updateTopic = false;
                }
            }
        } catch (WakeupException e) {
            //调用该consumer wakeup方法时，如果处于poll，回报这个错误。
        } finally {
            consumer.close();
        }
    }

    public void subscribe(String topic) {
        if (!topics.contains(topic)) {
            topics.add(topic);
            updateTopic = true;
        }
    }

    public void unSubscribe(String topic) {
        if (topics.contains(topic)) {
            topics.remove(topic);
            updateTopic = true;
        }
    }

    public void closeConsumer() {
        this.isRunning = false;
    }

    /**
     * 处理消息
     */
    public abstract void dealMessage(ConsumerRecord<String, String> record) throws Exception;
}
