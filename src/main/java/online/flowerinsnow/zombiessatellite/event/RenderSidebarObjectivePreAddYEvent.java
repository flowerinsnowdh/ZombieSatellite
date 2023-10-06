package online.flowerinsnow.zombiessatellite.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class RenderSidebarObjectivePreAddYEvent extends Event {
    public int addY;

    public RenderSidebarObjectivePreAddYEvent(int addY) {
        this.addY = addY;
    }
}
