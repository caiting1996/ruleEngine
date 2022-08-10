package com.example.ruleengine.actor.service;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import com.example.ruleengine.actor.share.AbstractActorProcessor;
import com.example.ruleengine.actor.share.ComponentLifeCycleProcessor;
import com.example.ruleengine.model.MsgModel;
import com.example.ruleengine.model.RuleNode;
import com.example.ruleengine.type.MsgType;
import org.springframework.stereotype.Component;

@Component
public class ComponentProcessor extends AbstractActorProcessor implements ComponentLifeCycleProcessor {

    @Override
    public void processor(Object msg) {

    }

    @Override
    public void onCreated(ActorContext context, MsgModel msg) throws Exception {
        RuleNode ruleNode= (RuleNode) msg.getMsg().get("node");
        ActorRef actorRef=actorSystemContext.getActorSystem().actorOf(actorSystemContext.getSpringExt().props(ruleNode.getType(),ruleNode.getConfiguration(),ruleNode.getSearchText(),ruleNode.getId()),ruleNode.getId().toString());
        actorSystemContext.getActorMap().put(ruleNode.getId().toString(),actorRef);
    }

    @Override
    public void onUpdate(ActorContext context) throws Exception {

    }

    @Override
    public void onActivate(ActorContext context) throws Exception {

    }

    @Override
    public void onSuspend(ActorContext context) throws Exception {

    }

    @Override
    public void onStop(ActorContext context,MsgModel msg) throws Exception {
        RuleNode ruleNode= (RuleNode) msg.getMsg().get("node");
        ActorRef actorRef=actorSystemContext.getActorSystem().actorSelection("akka://test-project/user/"+ruleNode.getId()).anchor();
        context.stop(actorRef);
    }
}
