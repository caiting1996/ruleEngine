package com.example.ruleengine.actor;

import akka.actor.*;
import com.example.ruleengine.actor.app.AppActor;
import com.example.ruleengine.config.AkkaConfig;
import com.example.ruleengine.model.MsgModel;
import com.example.ruleengine.model.RuleNode;
import com.example.ruleengine.ruleEngine.action.ActionJs;
import com.example.ruleengine.ruleEngine.filter.JsFilter;
import com.example.ruleengine.service.RuleEngineService;

import com.example.ruleengine.type.MsgType;
import com.example.ruleengine.utils.ScriptUtil;
import com.example.ruleengine.utils.SpringExt;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.FiniteDuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class ActorSystemContext implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ActorSystem actorSystem;
    @Autowired
    private JsFilter jsFilter;
    @Autowired
    private SpringExt springExt;
    @Autowired
    private ActionJs actionJs;
    @Autowired
    private AkkaConfig akkaConfig;
    @Autowired
    private RuleEngineService ruleEngineService;
    private Map<String,ActorRef> actorMap=new HashMap();
    private static final String APP="AppActor";
    private static final String COMPONENT="ComponentActor";

    //@PostConstruct
    //public void init(){
        //createActor("app", AppActor.class,actorSystem);
    //}


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent()==null){
            ApplicationContext context=contextRefreshedEvent.getApplicationContext();
            springExt.setApplicationContext(context);
            ActorRef appActor=actorSystem.actorOf(springExt.props(APP),APP);
            actorMap.put(APP,appActor);
            ActorRef componentActor=actorSystem.actorOf(springExt.props(COMPONENT),COMPONENT);
            actorMap.put(COMPONENT,componentActor);
        }
    }

    public ActorRef getOrCreateActor(RuleNode ruleNode){
        ActorRef actorRef=actorMap.get(ruleNode.getId().toString());
        if(actorRef==null){
            actorRef=actorSystem.actorOf(springExt.props(ruleNode.getType(),ruleNode.getConfiguration(),ruleNode.getSearchText(),ruleNode.getId()),ruleNode.getId().toString());
            actorMap.put(ruleNode.getId().toString(),actorRef);
        }
        return actorRef;
    }
}
