package me.thienbao860.expansion.spsexpansion.manager;

import me.thienbao860.expansion.spsexpansion.SpeedPerSecondExpansion;
import me.thienbao860.expansion.spsexpansion.type.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SPSManager {

    private final List<SPS> spsList = new ArrayList<>();

    public SPSManager(SpeedPerSecondExpansion expansion) {
        loadType();
    }

    public List<SPS> getSPSList() {
        return spsList;
    }

    public void update() {
        for (SPS sps : getSPSList()) {
            sps.updateSPS();
        }
    }

    public void loadType() {
        spsList.clear();
        spsList.add(new BlockBreaking());
        spsList.add(new BlockPlacing());
        spsList.add(new Speeding());
        spsList.add(new EntityDamaging());
        spsList.add(new ExpCollecting());
        spsList.add(new PlayerHurting());
        spsList.add(new Clicking());
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
