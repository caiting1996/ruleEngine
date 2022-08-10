package com.example.ruleengine.service;

import com.example.ruleengine.model.Relation;
import com.example.ruleengine.model.RuleChain;
import com.example.ruleengine.model.RuleNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleEngineService {
    List<Relation> getRelations(int fromId);
    List<RuleChain> getRuleChains();
    RuleNode getNodeById(int id);
    RuleChain getRuleChainById(int id);
    void insertRelation(Relation relation);
    void insertRuleChain(RuleChain ruleChain);
    void insertRuleNode(RuleNode ruleNode);
    void deleteRelation(int id);
    void deleteRuleChain(int id);
    void deleteRuleNode(int id);
}
