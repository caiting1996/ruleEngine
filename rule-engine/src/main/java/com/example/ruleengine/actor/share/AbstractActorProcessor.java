package com.example.ruleengine.actor.share;


import com.example.ruleengine.actor.ActorSystemContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.script.ScriptException;
import java.io.FileNotFoundException;

@Component
public class AbstractActorProcessor {
    @Autowired
    protected ActorSystemContext actorSystemContext;
    public void processor(Object msg) throws FileNotFoundException, ScriptException, NoSuchMethodException{}




}
