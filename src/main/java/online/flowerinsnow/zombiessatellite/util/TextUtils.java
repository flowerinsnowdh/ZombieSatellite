package online.flowerinsnow.zombiessatellite.util;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

public abstract class TextUtils {
    private TextUtils() {
    }

    public static char getColourOfHealth(List<String> patterns, float health) {
        for (String pattern : patterns) {
            int splitIndex;
            if ((splitIndex = pattern.indexOf(':')) == -1 || pattern.length() <= splitIndex + 1) {
                continue;
            }
            char colourCode = pattern.charAt(splitIndex + 1);
            if (pattern.startsWith("==")) {
                int value = Integer.parseInt(pattern.substring(2, splitIndex));
                if (health == value) {
                    return colourCode;
                }
            } else if (pattern.startsWith("!=")) {
                int value = Integer.parseInt(pattern.substring(2, splitIndex));
                if (health != value) {
                    return colourCode;
                }
            } else if (pattern.startsWith("<=")) {
                int value = Integer.parseInt(pattern.substring(2, splitIndex));
                if (health <= value) {
                    return colourCode;
                }
            } else if (pattern.startsWith(">=")) {
                int value = Integer.parseInt(pattern.substring(2, splitIndex));
                if (health >= value) {
                    return colourCode;
                }
            } else if (pattern.startsWith("<")) {
                int value = Integer.parseInt(pattern.substring(2, splitIndex));
                if (health < value) {
                    return colourCode;
                }
            } else if (pattern.startsWith(">")) {
                int value = Integer.parseInt(pattern.substring(2, splitIndex));
                if (health > value) {
                    return colourCode;
                }
            } else if (pattern.startsWith("default")) {
                return colourCode;
            }
        }
        return 'f';
    }

    public static boolean isInZombies() {
        ScoreObjective sidebar = Minecraft.getMinecraft().theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        return sidebar != null && ("ZOMBIES".equals(removeColourCode(sidebar.getDisplayName())));
    }

    public static String parseColourCode(String text) {
        return text.replace("&", "§")
                .replace("§§", "&");
    }

    public static String removeColourCode(String text) {
        return text.replaceAll("§.", "");
    }

    public static void addChatWithEvent(IChatComponent chatComponent) {
        ClientChatReceivedEvent event = new ClientChatReceivedEvent((byte) 0, chatComponent);
        if (!MinecraftForge.EVENT_BUS.post(event)) {
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(event.message);
        }
    }
}
