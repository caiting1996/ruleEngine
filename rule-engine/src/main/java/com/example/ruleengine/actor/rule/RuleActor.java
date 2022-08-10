package com.example.ruleengine.actor.rule;


import com.example.ruleengine.actor.share.AbstractActorAware;
import com.example.ruleengine.type.ActorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 规则引擎节点actor
 */
@Component("RuleActor")
@Scope("prototype")
public class RuleActor extends AbstractActorAware {

    @Autowired
    private RuleProcessor processor;
    public RuleActor(){}
    public RuleActor(String configuration,int type,Integer id){
        super(configuration,type,id);
    }

    @Override
    public void onReceive(Object message) throws Throwable {

        if(type==ActorType.FILTER.ordinal()){
            processor.filterProcessor(message,configuration,id);
        }else if(type==ActorType.ACTION.ordinal()){
            processor.actionProcessor(message,configuration,id);
        }


    }
}
