package me.thienbao860.expansion.spsexpansion;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.Cacheable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.thienbao860.expansion.spsexpansion.manager.SpeedType;
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
        return "1.1.1";
    }

    public String onRequest(OfflinePlayer p, String params) {

        if (p == null) return null;
        Player player = p.getPlayer();
        if (player == null) return null;
        startClock();
        switch (params) {
            case "blockBreak": return String.valueOf(toInt(manager.getSPS(player, SpeedType.BLOCK_BREAKING)));
            case "blockPlace": return String.valueOf(toInt(manager.getSPS(player, SpeedType.BLOCK_PLACING)));
            case "speed": return String.valueOf(toInt(manager.getSPS(player, SpeedType.SPEEDING)));
            case "damaging": return formatted(manager.getSPS(player, SpeedType.ENTITY_DAMAGING));
            case "hurting": return String.valueOf(toInt(manager.getSPS(player, SpeedType.PLAYER_HURT)));
            case "expCollected": return String.valueOf(toInt(manager.getSPS(player, SpeedType.EXP_COLLECTED)));
        }

        return null;
    }

    public void startClock() {
        if (!activate) {
            activate = true;
            task = Bukkit.getScheduler().runTaskTimerAsynchronously(PlaceholderAPIPlugin.getInstance(), () -> manager.update(), 0L, 20L);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        manager.addIn(e.getPlayer(), SpeedType.BLOCK_BREAKING);
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent e) {
        manager.addIn(e.getPlayer(), SpeedType.BLOCK_PLACING);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location from = e.getFrom().clone();
        Location to = e.getTo().clone();
        from.setY(0);
        to.setY(0);

        if (!from.getBlock().getLocation().equals(to.getBlock().getLocation())) {
            manager.addIn(e.getPlayer(), SpeedType.SPEEDING);
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            manager.addIn((Player) e.getDamager(), e.getFinalDamage(), SpeedType.ENTITY_DAMAGING);
        }
        if (e.getEntity() instanceof Player) {
            manager.addIn((Player) e.getEntity(), e.getFinalDamage(), SpeedType.PLAYER_HURT);
        }
    }

    @EventHandler
    public void onExpEarned(PlayerExpChangeEvent e) {
        manager.addIn(e.getPlayer(), e.getAmount(), SpeedType.EXP_COLLECTED);
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
