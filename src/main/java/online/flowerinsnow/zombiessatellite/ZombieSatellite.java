package online.flowerinsnow.zombiessatellite;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import online.flowerinsnow.zombiessatellite.command.CommandZombiesSatellite;
import online.flowerinsnow.zombiessatellite.config.Config;
import online.flowerinsnow.zombiessatellite.listener.RenderOverlayListener;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = ZombieSatellite.MODID, version = ZombieSatellite.VERSION)
public class ZombieSatellite {
    public static final String MODID = "zombiesatellite";
    public static final String VERSION = "@VERSION@";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Config.init(new File(event.getModConfigurationDirectory(), "zombiesatellite.xml"));
        Config.saveDefault();
        Config.load();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new RenderOverlayListener());
        ClientCommandHandler.instance.registerCommand(new CommandZombiesSatellite());
    }

    public static Logger getLogger() {
        return logger;
    }
}
