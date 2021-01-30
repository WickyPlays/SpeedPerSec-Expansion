package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Clicking extends SPS {

    @Override
    public String getName() {
        return "clicking";
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.LEFT_CLICK_AIR ||
                event.getAction() == Action.LEFT_CLICK_BLOCK) {

            addCache(player);
        }
    }
}
