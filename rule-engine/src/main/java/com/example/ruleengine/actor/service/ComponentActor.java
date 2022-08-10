package com.example.ruleengine.actor.service;

import com.example.ruleengine.actor.share.AbstractActorAware;
import com.example.ruleengine.model.MsgModel;
import com.example.ruleengine.type.MsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * actor生命周期管理
 */
@Component("ComponentActor")
@Scope("prototype")
public class ComponentActor extends AbstractActorAware {
    @Autowired
    private ComponentProcessor processor;

    @Override
    public void onReceive(Object msg) throws Throwable {
        MsgModel message=(MsgModel)msg;
        switch (message.getType()){
            case CREATE:
                processor.onCreated(getContext(),message);
            case DELETE:
                processor.onStop(getContext(),message);
        }
    }
}
