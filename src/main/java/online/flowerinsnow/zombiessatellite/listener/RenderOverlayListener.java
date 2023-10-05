package online.flowerinsnow.zombiessatellite.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import online.flowerinsnow.zombiessatellite.config.Config;
import online.flowerinsnow.zombiessatellite.util.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class RenderOverlayListener {
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (TextUtils.isInZombies()) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.theWorld.loadedEntityList.forEach(entity -> {
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entity;
                    if (player.isInvisible()) {
                        return;
                    }
                    if (!Pattern.compile("[a-zA-Z0-9_§]{3,18}").matcher(player.getName()).matches()) {
                        return;
                    }
                    event.right.add(Config.TeammatesHealth.text
                            .replace("&", "§")
                            .replace("§§", "&")
                            .replace("%(colour)", "§" + TextUtils.getColourOfHealth(Config.TeammatesHealth.colourPatterns, player.getHealth()))
                            .replace("%(player_name)", player.getName())
                            .replace("%(health)", new DecimalFormat("0.00").format(player.getHealth()))
                            .replace("%(max_health)", new DecimalFormat("0.00").format(player.getMaxHealth()))
                            .replace("%(distance)", Integer.toString(Math.round(player.getDistanceToEntity(mc.thePlayer))))
                    );

//                    String direction = getDirectionFromEntityToThePlayer(entity);
//
//                    float health = player.getHealth();
//
//                    int textX = Config.TeammatesHealth.x;
//                    int textY = Config.TeammatesHealth.y;
//
//                    int x = textX < 0 ? mc.displayWidth - textX : textX;
//                    int y = textY < 0 ? mc.displayHeight - textY : textY;

//                    GlStateManager.pushMatrix();
//                    GlStateManager.translate(x, y, 0.0F);
//                    GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//                    GlStateManager.enableBlend();
//                    FontRenderer fontRenderer = mc.fontRendererObj;
//                    fontRenderer.drawStringWithShadow(Config.TeammatesHealth.text
//                                    .replace("&", "§")
//                                    .replace("§§", "&")
//                                    .replace("%(colour)", "§" + TextUtils.getColourOfHealth(Config.TeammatesHealth.colourPatterns, health))
//                                    .replace("%(player_name)", player.getName())
//                                    .replace("%(health)", new DecimalFormat("0.00").format(player.getHealth()))
//                                    .replace("%(max_health)", new DecimalFormat("0.00").format(player.getMaxHealth()))
//                                    .replace("%(distance)", new DecimalFormat("0.00").format(player.getDistanceToEntity(mc.thePlayer))),
//                            x, y,
//                            0xFFFFFFFF
//                    );
//                    GlStateManager.disableBlend();
//                    GlStateManager.popMatrix();
                } else if (entity instanceof EntityGiantZombie) {
                    EntityGiantZombie giant = (EntityGiantZombie) entity;
                    event.right.add(Config.BossHealth.text
                            .replace("&", "§")
                            .replace("§§", "&")
                            .replace("%(colour)", "§" + TextUtils.getColourOfHealth(Config.BossHealth.colourPatterns, giant.getHealth()))
                            .replace("%(mob_name)", giant.getName())
                            .replace("%(health)", new DecimalFormat("0.00").format(giant.getHealth()))
                            .replace("%(max_health)", new DecimalFormat("0.00").format(giant.getMaxHealth()))
                            .replace("%(distance)", Integer.toString(Math.round(giant.getDistanceToEntity(mc.thePlayer))))
                    );
                }
            });
        }
    }

    /*
    private static String getDistanceStringFromEntityToThePlayer(Entity entity) {
        return entity.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) + "m";
    }
     */

    /*
    private static String getDirectionFromEntityToThePlayer(Entity entity) {
        EntityPlayerSP me = Minecraft.getMinecraft().thePlayer;

        double x = me.posX;
        double z = me.posZ;
        double targetX = entity.posX;
        double targetZ = entity.posZ;
        float yawDegrees = me.rotationYaw;

        double yawRadians = Math.toRadians(yawDegrees);

        double dirX = Math.cos(yawRadians);
        double dirZ = Math.sin(yawRadians);

        double vecX = targetX - x;
        double vecZ = targetZ - z;

        double dotProduct = dirX * vecX + dirZ * vecZ;
        double dirLength = Math.sqrt(dirX * dirX + dirZ * dirZ);
        double vecLength = Math.sqrt(vecX * vecX + vecZ * vecZ);
        double cosAngle = dotProduct / (dirLength * vecLength);
        double angleRadians = Math.acos(cosAngle);

        double angleDegrees = Math.toDegrees(angleRadians);

        if (angleDegrees >= -22.5 && angleDegrees < 22.5) {
            return "↑";
        } else if (angleDegrees >= 22.5 && yawDegrees < 67.5) {
            return "↗";
        } else if (angleDegrees >= 67.5 && angleDegrees < 112.5) {
            return "→";
        } else if (angleDegrees >= 112.5 && angleDegrees < 157.5) {
            return "↘";
        } else if ((angleDegrees >= 157.5 && angleDegrees <= 180.0) || (angleDegrees >= -180.0 && angleDegrees <= -157.5)) {
            return "↓";
        } else if (angleDegrees >= -157.5 && angleDegrees <= -112.5) {
            return "↙";
        } else if (angleDegrees >= -112.5 && angleDegrees <= -67.5) {
            return "←";
        } else if (angleDegrees >= -67.5 && angleDegrees <= -22.5) {
            return "↖";
        } else {
            return "●";
        }
    }
     */
}
