package com.example.ruleengine.ruleEngine;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;

public abstract class AbstractNodeService {

    private ScriptEngine engine= new ScriptEngineManager().getEngineByName("javascript");

    public <T> T startJs(Object msg,String config) throws NoSuchMethodException {
        T res=null;
        try {
            res= (T) execute(config).invokeFunction("execute",msg);
            return res;
        }catch (ScriptException e){
            System.out.println("表达式runtime错误:" + e.getMessage());
            return res;
        }

    }


    public Invocable execute(String config) throws ScriptException {
        String script=generateJs(config);
        engine.eval(script);
        if (engine instanceof Invocable) {
            Invocable invoke = (Invocable) engine;
            return invoke;
        } else {
            return null;
        }
    }


    public abstract String generateJs(String config );
}
