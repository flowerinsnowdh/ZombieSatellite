package online.flowerinsnow.zombiessatellite.hook;

import net.minecraft.launchwrapper.IClassTransformer;
import online.flowerinsnow.zombiessatellite.util.ASMUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class GuiIngameTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("net.minecraft.client.gui.GuiIngame".equals(transformedName)) {
            return ASMUtils.transformClass(basicClass, cn -> {
                cn.methods.forEach(mn -> {
                    if ("a".equals(mn.name) && "(Lauk;Lavr;)V".equals(mn.desc)) {
                        InsnList instructions = mn.instructions;
                        for (int i = 0; i < instructions.size(); i++) {
                            AbstractInsnNode node = instructions.get(i);
                            if (node.getOpcode() == Opcodes.ISTORE) {
                                VarInsnNode varInsnNode = (VarInsnNode) node;
                                if (varInsnNode.var == 8) {
                                    InsnList toInsert = new InsnList();
                                    toInsert.add(new TypeInsnNode(Opcodes.NEW, "online/flowerinsnow/zombiessatellite/event/RenderSidebarObjectiveDecidedEvent"));
                                    toInsert.add(new InsnNode(Opcodes.DUP));
                                    toInsert.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "online/flowerinsnow/zombiessatellite/event/RenderSidebarObjectiveDecidedEvent", "<init>", "(I)V", false));
                                    toInsert.add(new VarInsnNode(Opcodes.ASTORE, 20));
                                    toInsert.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/fml/common/eventhandler/EventBus;"));
                                    toInsert.add(new VarInsnNode(Opcodes.ALOAD, 20));
                                    toInsert.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraftforge/fml/common/eventhandler/EventBus", "post", "(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", false));
                                    toInsert.add(new InsnNode(Opcodes.POP));
                                    toInsert.add(new VarInsnNode(Opcodes.ALOAD, 20));
                                    toInsert.add(new FieldInsnNode(Opcodes.GETFIELD, "online/flowerinsnow/zombiessatellite/event/RenderSidebarObjectiveDecidedEvent", "bottom", "I"));
                                    toInsert.add(new VarInsnNode(Opcodes.ISTORE, 8));
                                    instructions.insert(varInsnNode, toInsert);
                                    return;
                                }
                            }
                        }
                    }
                });
            });
        }
        return basicClass;
    }
}
