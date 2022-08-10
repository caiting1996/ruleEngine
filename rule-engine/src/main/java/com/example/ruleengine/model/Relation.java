package com.example.ruleengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relation {
    private Integer id;
    private Integer fromId;
    private String fromType;
    private Integer toId;
    private String toType;
    private String relationTypeGroup;
    private String relationType;
    private String additionalInfo;
    private RuleNode ruleNode;

}
