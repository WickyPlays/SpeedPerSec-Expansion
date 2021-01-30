package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ExpCollecting extends SPS {

    public ExpCollecting() {
        super();
    }

    @Override
    public String getName() {
        return "expcollected";
    }

    @EventHandler
    public void onExpEarned(PlayerExpChangeEvent e) {
        addCache(e.getPlayer(), e.getAmount());
    }
}
