package com.example.ruleengine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Data
@Lazy
@Component
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfig {
    private String host;
    private String port;
    private String group_id;
}
