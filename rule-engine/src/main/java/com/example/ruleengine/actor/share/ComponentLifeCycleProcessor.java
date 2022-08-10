package com.example.ruleengine.actor.share;

import akka.actor.ActorContext;
import com.example.ruleengine.model.MsgModel;
import com.example.ruleengine.type.MsgType;

public interface ComponentLifeCycleProcessor {

    void onCreated(ActorContext context, MsgModel msg) throws Exception;

    void onUpdate(ActorContext context) throws Exception;

    void onActivate(ActorContext context) throws Exception;

    void onSuspend(ActorContext context) throws Exception;

    void onStop(ActorContext context,MsgModel msg) throws Exception;


}
