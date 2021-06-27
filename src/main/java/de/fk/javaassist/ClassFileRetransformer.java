package de.fk.javaassist;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassFileRetransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(Module module, ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (classBeingRedefined == Main.class) {
            CtClass ctClass = null;
            try {
                ClassPool cp = ClassPool.getDefault();
                cp.appendSystemPath();
                cp.appendClassPath(new ByteArrayClassPath(classBeingRedefined.getName(), classfileBuffer));
                ctClass = cp.get(classBeingRedefined.getName());
                ctClass.defrost();
                CtMethod tellMyAStoryMethod = ctClass.getDeclaredMethod("tellMyAStory");
                tellMyAStoryMethod.setBody("java.lang.System.out.println(\"Als Gregor Samsa eines Morgens aus unruhigen Träumen erwachte,\");");
                return ctClass.toBytecode();
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                if (ctClass != null) {
                    ctClass.detach();
                }
            }
        }
        return classfileBuffer;
    }

}
