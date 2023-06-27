package pl.edu.pwr.mniewczas.window;

import javax.script.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyScriptManager {

    private final ScriptEngineManager manager = new ScriptEngineManager();
    private final ScriptEngine engine = manager.getEngineByName("graal.js");
    private String scriptContent;

    public void loadScript(Path scriptPath){

        try {
            scriptContent = Files.readString(scriptPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //true - alive, false - dead
    public boolean checkCellStateInNextGeneration(String array) throws ScriptException, NoSuchMethodException {
        engine.getContext().getBindings(ScriptContext.ENGINE_SCOPE).put("arr", array);
        engine.eval(scriptContent);
        Invocable invocable = (Invocable) engine;
        Object result = invocable.invokeFunction("gameOfLife", array);
        boolean resultBoolean = (boolean) result;

        return resultBoolean;
    }


}
