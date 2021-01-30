package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamaging extends SPS {

    public EntityDamaging() {
        super();
    }

    @Override
    public String getName() {
        return "damaging";
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            addCache((Player) e.getDamager(), e.getFinalDamage());
        }
    }

}
