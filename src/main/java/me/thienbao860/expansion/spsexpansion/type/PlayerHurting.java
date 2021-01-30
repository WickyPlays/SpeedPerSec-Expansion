package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHurting extends SPS {

    public PlayerHurting() {
        super();
    }

    @Override
    public String getName() {
        return "hurting";
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            addCache((Player) e.getEntity(), e.getFinalDamage());
        }
    }
}
