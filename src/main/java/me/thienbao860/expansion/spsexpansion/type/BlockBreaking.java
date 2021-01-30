package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreaking extends SPS {

    public BlockBreaking() {
        super();
    }

    @Override
    public String getName() {
        return "blockbreak";
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        addCache(e.getPlayer());
    }

}
