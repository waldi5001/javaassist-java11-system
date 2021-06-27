package de.fk.javaassist;

import java.lang.instrument.Instrumentation;

public class Javaagent {

    public static void premain(String agentArgs, Instrumentation inst) {
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        if (inst.isRetransformClassesSupported()) {
            inst.addTransformer(new ClassFileRetransformer(), true);
            try {
                for (Class loadedClass : inst.getAllLoadedClasses()) {
                    if (loadedClass == Main.class) {
                        inst.retransformClasses(loadedClass);
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

}
