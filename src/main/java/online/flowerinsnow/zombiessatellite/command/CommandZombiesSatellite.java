package online.flowerinsnow.zombiessatellite.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import online.flowerinsnow.zombiessatellite.config.Config;
import online.flowerinsnow.zombiessatellite.util.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandZombiesSatellite extends CommandBase {
    @Override
    public String getCommandName() {
        return "zombiessatellite";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "command.zombiessatellite.usage";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender.equals(Minecraft.getMinecraft().thePlayer);
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1 && "reload".startsWith(args[0].toLowerCase())) {
            return new ArrayList<>(Collections.singletonList("reload"));
        }
        return new ArrayList<>();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            Config.load();
            TextUtils.addChatWithEvent(new ChatComponentTranslation("command.zombiessatellite.reload"));
            return;
        }
        throw new WrongUsageException(getCommandUsage(sender));
    }
}
