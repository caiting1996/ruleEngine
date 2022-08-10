package com.example.ruleengine.service.impl;

import com.example.ruleengine.mapper.RuleEngineMapper;
import com.example.ruleengine.model.Relation;
import com.example.ruleengine.model.RuleChain;
import com.example.ruleengine.model.RuleNode;
import com.example.ruleengine.service.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleEngineServiceImpl implements RuleEngineService {
    @Autowired
    private RuleEngineMapper ruleEngineMapper;
    @Override
    public List<Relation> getRelations(int fromId) {
        List<Relation> relations=ruleEngineMapper.getRelations(fromId);
        return relations;
    }

    @Override
    public List<RuleChain> getRuleChains() {
        List<RuleChain> ruleChains=ruleEngineMapper.getRuleChains();
        return ruleChains;
    }

    @Override
    public RuleNode getNodeById(int id) {
        RuleNode ruleNode=ruleEngineMapper.getNodeById(id);
        return ruleNode;
    }

    @Override
    public RuleChain getRuleChainById(int id) {
        RuleChain ruleChain=ruleEngineMapper.getRuleChainById(id);
        return ruleChain;
    }

    @Override
    public void insertRelation(Relation relation) {
        ruleEngineMapper.insertRelation(relation);
    }

    @Override
    public void insertRuleChain(RuleChain ruleChain) {
        ruleEngineMapper.insertRuleChain(ruleChain);
    }

    @Override
    public void insertRuleNode(RuleNode ruleNode) {
        ruleEngineMapper.insertRuleNode(ruleNode);
    }

    @Override
    public void deleteRelation(int id) {
        ruleEngineMapper.deleteRelation(id);
    }

    @Override
    public void deleteRuleChain(int id) {
        ruleEngineMapper.deleteRuleChain(id);
    }

    @Override
    public void deleteRuleNode(int id) {
        ruleEngineMapper.deleteRuleNode(id);
    }
}
