package com.example.ruleengine.message.config;


public class KafkaProducerConfig {
    private String host;
    private String port;
    private Class keySerialize;
    private Class valueSerialize;
    public KafkaProducerConfig(){}
    public KafkaProducerConfig(String host,String port,Class keySerialize,Class valueSerialize){
        this.host=host;
        this.port=port;
        this.keySerialize=keySerialize;
        this.valueSerialize=valueSerialize;
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

    public Class getKeySerialize() {
        return keySerialize;
    }

    public void setKeySerialize(Class keyDeserialize) {
        this.keySerialize = keyDeserialize;
    }

    public Class getValueSerialize() {
        return valueSerialize;
    }

    public void setValueSerialize(Class valueDeserialize) {
        this.valueSerialize = valueDeserialize;
    }
}
