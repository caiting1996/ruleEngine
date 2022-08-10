package com.example.ruleengine;

import com.example.ruleengine.message.consumer.ConsumerClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.ruleengine.mapper")
public class RuleEngineApplication implements CommandLineRunner
        {
    @Autowired
    private ConsumerClient consumerClient;
    public static void main(String[] args) {

        SpringApplication.run(RuleEngineApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        try {
            consumerClient.subscribe("rule");
            consumerClient.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
