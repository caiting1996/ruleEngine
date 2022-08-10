package com.example.ruleengine.utils;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

@Component
public class ScriptUtil {

    public static String LUA_PREFIX="function execute(msg){\n";
    public static String LUA_END="\n}";
    //private ScriptEngineManager manager = new ScriptEngineManager();
    //private ScriptEngine engine= manager.getEngineByName("JavaScript");
    private String fileName;
    private String script;
    private ScriptEngine engine1;

    private static NashornScriptEngineFactory factory=new NashornScriptEngineFactory() ;

   // public void RunScript(String fileName){
        //engine = manager.getEngineByName("JavaScript");
        //this.fileName = fileName;
    //}


    public ScriptUtil(){}
    public ScriptUtil(String config) {
        engine = compileScript(generateJs(config));
        script=generateJs(config);
        engine1 = new ScriptEngineManager().getEngineByName("javascript");
    }

    private CompiledScript engine;



    private static CompiledScript compileScript(String script) {
        ScriptEngine engine = factory.getScriptEngine(new String[]{"--no-java"});
        Compilable compEngine = (Compilable) engine;
        try {
            return compEngine.compile(script);
        } catch (ScriptException e) {

            throw new IllegalArgumentException("Can't compile script: " + e.getMessage());
        }
    }

    public Boolean execute(Bindings bindings) throws ScriptException {
        Object eval = engine.eval(bindings);
        if (eval instanceof Boolean) {
            return (Boolean) eval;
        } else {

            throw new ScriptException("Wrong result type: " + eval);
        }
    }



    public boolean startJs(Object msg) throws FileNotFoundException, ScriptException, NoSuchMethodException {

        Boolean res=false;
        try {
            engine1.eval(script);
            if (engine1 instanceof Invocable) {
                Invocable invoke = (Invocable) engine1;
                res =(Boolean) invoke.invokeFunction("execute", msg);
                System.out.println(res);
            } else {
                System.out.println("error");
            }
        } catch (ScriptException e) {
            System.out.println("表达式runtime错误:" + e.getMessage());
        }
        System.out.println("执行完成");
        return res;
    }

    public boolean startJs1(Object msg,String config) throws FileNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngine engine= factory.getScriptEngine(new String[]{"--no-java"});
        Compilable compEngine = (Compilable) engine;
        CompiledScript en=compEngine.compile(script);
        Bindings bindings = new SimpleBindings();
        bindings.put("key",msg);
        Object eval= en.eval();
        if (eval instanceof Boolean) {
            return (Boolean) eval;
        } else {

            throw new ScriptException("Wrong result type: " + eval);
        }
    }
    public String generateJs(String config ){
        String script=LUA_PREFIX+config+LUA_END;


        return script ;
    }
    public Globals generateLua(String config ,Globals globalsIn){
        String script=LUA_PREFIX+config+LUA_END;

        globalsIn.load(script).call();
        return globalsIn ;
    }
    public Boolean executeScript(Object message,String config,Globals globalsIn){
        Globals globals=generateLua(config,globalsIn);
        LuaValue func = globals.get(LuaValue.valueOf("execute"));
        LuaTable luaTable=new LuaTable();
        Map msg= (Map) message;
        Iterator<Map.Entry< String,String>> iterator =msg.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry< String,String> entry = iterator.next();
            luaTable.set(entry.getKey(),entry.getValue());
        }

        LuaValue res=func.call(luaTable);
        System.out.println(CoerceLuaToJava.coerce(res, Boolean.class));
        return (Boolean) CoerceLuaToJava.coerce(res, Boolean.class);
    }
    public Boolean executeScript1(Object message,String config){

        return true;
    }


}
