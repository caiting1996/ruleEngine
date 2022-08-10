package com.example.ruleengine.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import com.example.ruleengine.actor.ActorSystemContext;
import com.example.ruleengine.model.*;
import com.example.ruleengine.service.RuleEngineService;
import com.example.ruleengine.type.MsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/ruleEngine/")
public class Controller {
    @Autowired
    private ActorSystemContext actorSystemContext;
    @Autowired
    private RuleEngineService ruleEngineService;
    private static final String COMPONENT="ComponentActor";
    @PostMapping("addRuleNode")
    public ResponseModel createRuleNode(@RequestBody RuleNode ruleNode){
        ActorSelection selection=actorSystemContext.getActorSystem().actorSelection(actorSystemContext.getAkkaConfig().getActor_path()+COMPONENT);
        Map map=new HashMap();
        map.put("node",ruleNode);
        MsgModel msg = MsgModel.builder().type(MsgType.CREATE).msg(map).build();
        selection.tell(msg, ActorRef.noSender());
        ruleEngineService.insertRuleNode(ruleNode);
        return new ResponseModel("success");

    }
    @PostMapping("addRuleChain")
    public ResponseModel createRuleChain(@RequestBody RuleChain ruleChain){
        ruleEngineService.insertRuleChain(ruleChain);
        return new ResponseModel("success");
    }
    @PostMapping("addRelation")
    public ResponseModel createRelation(@RequestBody Relation relation){
        ruleEngineService.insertRelation(relation);
        return new ResponseModel("success");
    }
    @PostMapping("deleteNode")
    public ResponseModel deleteNode(@RequestBody RuleNode ruleNode){
        ActorSelection selection=actorSystemContext.getActorSystem().actorSelection(actorSystemContext.getAkkaConfig().getActor_path()+COMPONENT);
        Map map=new HashMap();
        map.put("node",ruleNode);
        MsgModel msg = MsgModel.builder().type(MsgType.DELETE).msg(map).build();
        return new ResponseModel("success");
    }

}
