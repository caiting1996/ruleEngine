package com.example.ruleengine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Data
@Lazy
@Component
@ConfigurationProperties(prefix = "akka-config")
public class AkkaConfig {
    String actor_path;
    String actorSystem_name;
}
