package com.example.ruleengine.actor.app;


import com.example.ruleengine.actor.share.AbstractActorAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 应用消息接收入口actor
 */
@Component("AppActor")
@Scope("prototype")
public class AppActor extends AbstractActorAware {
    @Autowired
    private AppProcessor processor;

    @Override
    public void onReceive(Object msg) throws Throwable {
        processor.processor(msg);
    }
}
