package me.thienbao860.expansion.spsexpansion.manager;

import me.thienbao860.expansion.spsexpansion.type.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TypeManager {

    private List<SPS> spsList = new ArrayList<>();
    private static TypeManager instance;

    public TypeManager() {
        loadType();
    }

    public static TypeManager instance() {
        if (instance == null) {
            instance = new TypeManager();
        }
        return instance;
    }

    public void update() {
        for (SPS sps : spsList) {
            sps.updateSPS();
        }
    }

    public void addIn(Player player, String typeName) {
        addIn(player, 1, typeName);
    }

    public void addIn(Player player, double value, String typeName) {
        SPS sps = getSPSByName(typeName);
        if (sps == null) return;

        sps.addCache(player, value);
    }

    public void loadType() {
        spsList.clear();
        spsList.add(new BlockBreaking());
        spsList.add(new BlockPlacing());
        spsList.add(new Speeding());
        spsList.add(new EntityDamaging());
        spsList.add(new ExpCollecting());
        spsList.add(new PlayerHurting());
    }

    public double getSPSValue(Player player, String name) {
        SPS sps = getSPSByName(name);
        if (sps != null) {
            return sps.getCurrSPS(player);
        }

        return 0;
    }

    public SPS getSPSByName(String spsName) {
        return spsList.stream().filter(p -> p.getName().equalsIgnoreCase(spsName)).findFirst().orElse(null);
    }

    public void clearSPSFrom(Player player) {
        for (SPS sps : spsList) {
            sps.getSPSMap().remove(player.getUniqueId());
        }
    }

}
