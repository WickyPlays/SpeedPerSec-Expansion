package me.thienbao860.expansion.spsexpansion.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public abstract class SPS {

    private final HashMap<UUID, Double> cache;
    private final HashMap<UUID, Double> spsMap;
    
    public SPS() {
        cache = new HashMap<>();
        spsMap = new HashMap<>();
    }

    public abstract String getName();

    public void addCache(Player player, double d) {
        UUID uuid = player.getUniqueId();
        if (cache.containsKey(uuid)) cache.replace(player.getUniqueId(), cache.get(uuid) + d);
    }

    public void updateSPS() {
        for (UUID uuid : cache.keySet()) {
            if (spsMap.containsKey(uuid)) spsMap.replace(uuid, cache.get(uuid));
            else spsMap.put(uuid, cache.get(uuid));
        }
        cache.clear();
        for (Player player : Bukkit.getOnlinePlayers()) {
            cache.put(player.getUniqueId(), 0.0);
        }
    }

    public double getCurrSPS(Player player) {
        UUID uuid = player.getUniqueId();
        return spsMap.get(uuid) != null ? spsMap.get(uuid) : 0;
    }

    public HashMap<UUID, Double> getSPSMap() {
        return spsMap;
    }
}
