package online.flowerinsnow.zombiessatellite.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.function.Consumer;

public abstract class ASMUtils {
    private ASMUtils() {
    }

    public static byte[] transformClass(byte[] basicClass, Consumer<ClassNode> action) {
        ClassReader cr = new ClassReader(basicClass);
        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.EXPAND_FRAMES);
        action.accept(cn);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
