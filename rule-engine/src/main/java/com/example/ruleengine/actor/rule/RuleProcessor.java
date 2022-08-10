package com.example.ruleengine.actor.rule;

import akka.actor.ActorRef;
import com.example.ruleengine.actor.share.AbstractActorProcessor;
import com.example.ruleengine.model.Relation;
import org.springframework.stereotype.Component;

import javax.script.Bindings;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.util.List;

@Component
public class RuleProcessor extends AbstractActorProcessor {

    public void filterProcessor(Object msg,String configuration,Integer id) throws NoSuchMethodException, ScriptException {
        Boolean res=actorSystemContext.getJsFilter().startJs(msg,configuration);
        System.out.println(res);
        List<Relation> relations=actorSystemContext.getRuleEngineService().getRelations(id);
        for (Relation relation:relations){
            if((relation.getRelationType().equals(res.toString()))){
                actorSystemContext.getOrCreateActor(relation.getRuleNode())
                        .tell(msg, ActorRef.noSender());
            }
        }

    }

    public void actionProcessor(Object msg,String configuration,Integer id) throws NoSuchMethodException {
        actorSystemContext.getActionJs().startJs(msg,null);
    }


}
