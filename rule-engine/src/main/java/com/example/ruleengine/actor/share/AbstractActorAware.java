package com.example.ruleengine.actor.share;

import akka.actor.UntypedActor;

public abstract class AbstractActorAware extends UntypedActor {

    protected String configuration;
    protected int type;
    protected Integer id;
    public AbstractActorAware(){}
    public AbstractActorAware(String configuration,int type,Integer id){

        this.configuration=configuration;
        this.type=type;
        this.id=id;
    }

}
