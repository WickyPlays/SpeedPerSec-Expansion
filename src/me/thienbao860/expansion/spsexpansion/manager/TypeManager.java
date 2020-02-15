package me.thienbao860.expansion.spsexpansion.manager;

import me.thienbao860.expansion.spsexpansion.type.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TypeManager {

    private List<SPS> list = new ArrayList<>();
    private static TypeManager instance;

    public TypeManager() {
        loadType();
    }

    public static TypeManager instance() {
        if (instance == null) instance = new TypeManager();
        return instance;
    }

    public void update() {
        for (SPS sps : list) {
            sps.updateSPS();
        }
    }

    public void addIn(Player player, SpeedType type) {
        addIn(player, 1, type);

    }

    public void addIn(Player player, double value, SpeedType type) {
        for (SPS sps : list) {
            if (sps.getType() == type) {
                sps.addCache(player, value);
                break;
            }
        }
    }

    public void loadType() {
        list.clear();
//        list.add(new LeftClicking());
//        list.add(new RightClicking());
        list.add(new BlockBreaking());
        list.add(new BlockPlacing());
        list.add(new Speeding());
        list.add(new EntityDamaging());
        list.add(new ExpCollecting());
    }

    public double getSPS(Player player, SpeedType type) {
        for (SPS sps : list) {
            if (sps.getType() == type) {
                return sps.getCurrSPS(player);
            }
        }
        return 0;
    }

    public void clearSPSFrom(Player player) {
        for (SPS sps : list) {
            sps.sps.remove(player.getUniqueId());

        }
    }

}
