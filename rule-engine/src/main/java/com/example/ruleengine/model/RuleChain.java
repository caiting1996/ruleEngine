package com.example.ruleengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleChain {
    private Integer id;
    private String additionalInfo;
    private String configuration;
    private String name;
    private Integer firstRuleNodeId;
    private boolean root;
    private boolean debugMode;
    private String searchText;
    private String tenantId;
    private RuleNode ruleNode;
}
