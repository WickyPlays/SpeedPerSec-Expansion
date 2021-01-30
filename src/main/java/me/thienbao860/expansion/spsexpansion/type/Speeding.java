package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class Speeding extends SPS {

    public Speeding() {
        super();
    }

    @Override
    public String getName() {
        return "speeding";
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Location from = event.getFrom().clone();
        Location to = event.getTo();

        if (to == null) return;
        to = to.clone();

        from.setY(0);
        to.setY(0);

        if (!from.getBlock().getLocation().equals(to.getBlock().getLocation())) {
            addCache(event.getPlayer());
        }
    }

}
