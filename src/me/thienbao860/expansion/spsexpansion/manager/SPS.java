package me.thienbao860.expansion.spsexpansion.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public abstract class SPS {

    public HashMap<UUID, Double> cache;
    public HashMap<UUID, Double> sps;
    
    public SPS() {
        cache = new HashMap<>();
        sps = new HashMap<>();
    }

    public void addCache(Player player, double d) {
        UUID uuid = player.getUniqueId();
        if (cache.containsKey(uuid)) cache.replace(player.getUniqueId(), cache.get(uuid) + d);
    }

    public void updateSPS() {
        for (UUID uuid : cache.keySet()) {
            if (sps.containsKey(uuid)) sps.replace(uuid, cache.get(uuid));
            else sps.put(uuid, cache.get(uuid));
        }
        cache.clear();
        for (Player player : Bukkit.getOnlinePlayers()) {
            cache.put(player.getUniqueId(), 0.0);
        }
    }

    public double getCurrSPS(Player player) {
        UUID uuid = player.getUniqueId();
        return sps.get(uuid) != null ? sps.get(uuid) : 0;
    }

    public abstract SpeedType getType();
}
