package me.thienbao860.expansion.spsexpansion;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.Cacheable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.thienbao860.expansion.spsexpansion.manager.TypeManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SpeedPerSecondExpansion extends PlaceholderExpansion implements Listener, Cacheable {

    private boolean activate;
    private final TypeManager manager;
    private BukkitTask task;

    public SpeedPerSecondExpansion() {
        activate = false;
        manager = TypeManager.instance();
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
        return "1.2.0";
    }

    public String onRequest(OfflinePlayer op, String params) {
        Player player = op.getPlayer();
        if (player == null) return null;

        return String.valueOf(getVal(player, params));
    }

    public int getVal(Player player, String typeName) {
        return (int) manager.getSPSValue(player, typeName);
    }

    public void startClock() {
        if (!activate) {
            activate = true;
            task = Bukkit.getScheduler().runTaskTimerAsynchronously(PlaceholderAPIPlugin.getInstance(), manager::update, 0L, 20L);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        manager.addIn(e.getPlayer(), "blockbreak");
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent e) {
        manager.addIn(e.getPlayer(), "blockplace");
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location from = e.getFrom().clone();
        Location to = e.getTo().clone();
        from.setY(0);
        to.setY(0);

        if (!from.getBlock().getLocation().equals(to.getBlock().getLocation())) {
            manager.addIn(e.getPlayer(), "speeding");
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            manager.addIn((Player) e.getDamager(), e.getFinalDamage(), "damaging");
        }
        if (e.getEntity() instanceof Player) {
            manager.addIn((Player) e.getEntity(), e.getFinalDamage(), "hurting");
        }
    }

    @EventHandler
    public void onExpEarned(PlayerExpChangeEvent e) {
        manager.addIn(e.getPlayer(), e.getAmount(), "expcollected");
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent event) {
        manager.clearSPSFrom(event.getPlayer());
    }

    @Override
    public void clear() {
        activate = true;
        if (task != null) task.cancel();
    }

    public int toInt(double d) {
        return (int) d;
    }

    private String formatted(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(d);
    }
}
