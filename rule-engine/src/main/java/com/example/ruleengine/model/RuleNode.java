package com.example.ruleengine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleNode {
    private Integer id;
    private Integer ruleChainId;
    private String additionalInfo;
    private String configuration;
    private String type;
    private String name;
    private boolean debugMode;
    private int searchText;

}
