package com.example.ruleengine.ruleEngine.action;

import com.example.ruleengine.ruleEngine.AbstractNodeService;
import org.springframework.stereotype.Component;

@Component
public class ActionJs extends AbstractNodeService {
    public static String ACTION_PREFIX=
            "load(\"nashorn:mozilla_compat.js\");\n" +
                    "importPackage(com.example.ruleengine.ruleEngine.action)\n" +
                    "importClass(com.example.ruleengine.ruleEngine.action.ActionContent)\n"+
            "function execute(msg){\n" +
            //"var ActionContent=Java.type(com.example.ruleengine.ruleEngine.action.ActionContent)\n" +
                    "var content=new com.example.ruleengine.ruleEngine.action.ActionContent()\n" +
                    "content.execute(msg.param)";
    public static String ACTION_END="\n}";
    @Override
    public String generateJs(String config) {
        return ACTION_PREFIX+ACTION_END;
    }
}
