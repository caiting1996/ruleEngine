package com.example.ruleengine.mapper;

import com.example.ruleengine.model.Relation;
import com.example.ruleengine.model.RuleChain;
import com.example.ruleengine.model.RuleNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleEngineMapper {
    List<Relation> getRelations(@Param("fromId") int fromId);
    List<RuleChain> getRuleChains();
    RuleNode getNodeById(@Param("id") int id);
    RuleChain getRuleChainById(@Param("id") int id);
    void insertRelation(@Param("relation") Relation relation);
    void insertRuleChain(@Param("ruleChain") RuleChain ruleChain);
    void insertRuleNode(@Param("ruleNode") RuleNode ruleNode);
    void deleteRelation(@Param("id") int id);
    void deleteRuleChain(@Param("id") int id);
    void deleteRuleNode(@Param("id") int id);

}
