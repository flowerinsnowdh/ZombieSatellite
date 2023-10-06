package online.flowerinsnow.zombiessatellite.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.common.MinecraftForge;
import online.flowerinsnow.zombiessatellite.event.RenderSidebarObjectivePreAddYEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiIngame.class)
public class MixinGuiIngame extends Gui {
    @Redirect(
            method = "renderScoreboard",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiIngame;drawRect(IIIII)V",
                    ordinal = 0
            )
    )
    public void redirectK0(int left, int top, int right, int bottom, int color) {
        RenderSidebarObjectivePreAddYEvent event = new RenderSidebarObjectivePreAddYEvent(0);
        MinecraftForge.EVENT_BUS.post(event);
        drawRect(left, top + event.addY, right, bottom + event.addY, color);
    }

    @Redirect(
            method = "renderScoreboard",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I",
                    ordinal = 0
            )
    )
    public int redirectK1(FontRenderer instance, String text, int x, int y, int color) {
        RenderSidebarObjectivePreAddYEvent event = new RenderSidebarObjectivePreAddYEvent(0);
        MinecraftForge.EVENT_BUS.post(event);
        return instance.drawString(text, x, y + event.addY, color);
    }

    @Redirect(
            method = "renderScoreboard",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I",
                    ordinal = 1
            )
    )
    public int redirectK2(FontRenderer instance, String text, int x, int y, int color) {
        RenderSidebarObjectivePreAddYEvent event = new RenderSidebarObjectivePreAddYEvent(0);
        MinecraftForge.EVENT_BUS.post(event);
        return instance.drawString(text, x, y + event.addY, color);
    }

    @Redirect(
            method = "renderScoreboard",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiIngame;drawRect(IIIII)V",
                    ordinal = 1
            )
    )
    public void redirectK3(int left, int top, int right, int bottom, int color) {
        RenderSidebarObjectivePreAddYEvent event = new RenderSidebarObjectivePreAddYEvent(0);
        MinecraftForge.EVENT_BUS.post(event);
        drawRect(left, top + event.addY, right, bottom + event.addY, color);
    }

    @Redirect(
            method = "renderScoreboard",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiIngame;drawRect(IIIII)V",
                    ordinal = 2
            )
    )
    public void redirectK4(int left, int top, int right, int bottom, int color) {
        RenderSidebarObjectivePreAddYEvent event = new RenderSidebarObjectivePreAddYEvent(0);
        MinecraftForge.EVENT_BUS.post(event);
        drawRect(left, top + event.addY, right, bottom + event.addY, color);
    }

    @Redirect(
            method = "renderScoreboard",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I",
                    ordinal = 2
            )
    )
    public int redirectK5(FontRenderer instance, String text, int x, int y, int color) {
        RenderSidebarObjectivePreAddYEvent event = new RenderSidebarObjectivePreAddYEvent(0);
        MinecraftForge.EVENT_BUS.post(event);
        return instance.drawString(text, x, y + event.addY, color);
    }
}
