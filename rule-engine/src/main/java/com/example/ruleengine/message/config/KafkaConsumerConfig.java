package com.example.ruleengine.message.config;


public class KafkaConsumerConfig {
    private String host;
    private String port;
    private String groupId;
    private Class keyDeserialize;
    private Class valueDeserialize;

    public KafkaConsumerConfig() {
    }

    public KafkaConsumerConfig(String host, String port, String groupId, Class keyDeserialize, Class valueDeserialize) {
        this.host = host;
        this.port = port;
        this.groupId = groupId;
        this.keyDeserialize = keyDeserialize;
        this.valueDeserialize = valueDeserialize;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Class getKeyDeserialize() {
        return keyDeserialize;
    }

    public void setKeyDeserialize(Class keyDeserialize) {
        this.keyDeserialize = keyDeserialize;
    }

    public Class getValueDeserialize() {
        return valueDeserialize;
    }

    public void setValueDeserialize(Class valueDeserialize) {
        this.valueDeserialize = valueDeserialize;
    }

    @Override
    public String toString() {
        return "ConsumerConfig{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", groupId='" + groupId + '\'' +
                ", keyDeserialize=" + keyDeserialize +
                ", valueDeserialize=" + valueDeserialize +
                '}';
    }
}
