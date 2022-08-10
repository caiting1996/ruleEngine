package com.example.ruleengine.actor.app;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import com.example.ruleengine.actor.share.AbstractActorProcessor;
import com.example.ruleengine.model.RuleChain;
import com.example.ruleengine.service.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.Bindings;

import javax.script.SimpleBindings;
import java.util.List;


@Component
public class AppProcessor extends AbstractActorProcessor {
    @Autowired
    private RuleEngineService ruleEngineService;
    @Override
    public void processor(Object msg) throws NoSuchMethodException {
        List<RuleChain> ruleChains=ruleEngineService.getRuleChains();
        for (RuleChain r:ruleChains){
            actorSystemContext.getOrCreateActor(r.getRuleNode()).tell(msg,ActorRef.noSender());
        }

    }
}
