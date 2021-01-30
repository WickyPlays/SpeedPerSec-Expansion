package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlacing extends SPS {

    public BlockPlacing() {
        super();
    }

    @Override
    public String getName() {
        return "blockplace";
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent e) {
        addCache(e.getPlayer());
    }

}
