package me.thienbao860.expansion.spsexpansion;

import me.clip.placeholderapi.expansion.Cacheable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.thienbao860.expansion.spsexpansion.manager.SPSManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

public class SpeedPerSecondExpansion extends PlaceholderExpansion implements Listener, Cacheable {

    private boolean activate;
    private final SPSManager manager;
    private BukkitTask task;

    public SpeedPerSecondExpansion() {
        this.activate = false;
        this.manager = new SPSManager(this);
        startClock();
    }

    @Override
    public String getIdentifier() {
        return "speedpersec";
    }

    @Override
    public String getAuthor() {
        return "thienbao860";
    }

    @Override
    public String getVersion() {
        return "1.3.0";
    }

    @Override
    public boolean register() {
        startClock();
        return super.register();
    }

    @Override
    public String onRequest(OfflinePlayer op, String params) {
        Player player = op.getPlayer();
        String[] args = params.split("_");
        if (player == null) {
            return null;
        }

        if (args.length == 2) {
            switch (args[1].toLowerCase()) {
                case "int":
                    return String.valueOf(toInt(getVal(player, args[0])));
                case "double":
                    return String.valueOf(formatted(getVal(player, args[0])));
            }
        }

        return String.valueOf(getVal(player, args[0]));
    }

    public double getVal(Player player, String typeName) {
        return manager.getSPSValue(player, typeName);
    }

    public void startClock() {
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(
                this.getPlaceholderAPI(),
                manager::update, 0L, 20L);

    }

    @EventHandler
    public void onLeft(PlayerQuitEvent event) {
        manager.clearSPSFrom(event.getPlayer());
    }

    @Override
    public void clear() {
        if (task != null) {
            task.cancel();
            task = null;
            manager.getSPSList().clear();
        }
    }

    public int toInt(double d) {
        return (int) d;
    }

    private String formatted(double d) {
        return String.format("%.2f", d);
    }
}
