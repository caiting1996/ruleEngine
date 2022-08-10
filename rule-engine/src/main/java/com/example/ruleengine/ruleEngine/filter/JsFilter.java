package com.example.ruleengine.ruleEngine.filter;

import com.example.ruleengine.ruleEngine.AbstractNodeService;
import org.springframework.stereotype.Component;

@Component
public class JsFilter extends AbstractNodeService  {
    public static String FILTER_PREFIX="function execute(msg){\n";
    public static String FILTER_END="\n}";
    @Override
    public String generateJs(String config) {
        return FILTER_PREFIX+config+FILTER_END;
    }

}
