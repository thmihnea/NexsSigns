package by.thmihnea.nexssigns.cooldown;

import by.thmihnea.nexssigns.NexsSigns;
import by.thmihnea.nexssigns.TimeUtil;
import by.thmihnea.nexssigns.cache.PlayerRepository;
import by.thmihnea.nexssigns.object.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Cooldown implements Runnable {

    private Player player;
    private CooldownType cooldownType;
    private long seconds;
    private long endTime;
    private BukkitTask bukkitTask;
    private NexsSigns plugin;

    public Cooldown(Player player, CooldownType cooldownType, long seconds, NexsSigns plugin) {
        this.player = player;
        this.cooldownType = cooldownType;
        this.seconds = seconds;
        this.plugin = plugin;
        this.endTime = System.currentTimeMillis() + (1000 * seconds);
        this.bukkitTask = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);
    }

    @Override
    public void run() {
        if (this.isOver()) {
            this.clear();
        }
    }

    public boolean isOver() {
        return System.currentTimeMillis() >= this.endTime;
    }

    public CooldownType getCooldownType() {
        return this.cooldownType;
    }

    public long timeLeft() {
        return (endTime - System.currentTimeMillis());
    }

    public long timeLeftInSeconds() {
        return timeLeft() / 1000;
    }

    public void sendCooldownMessage() {
        String message = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfigManager().getString("cooldown-message"));
        message = message.replace("{timeLeft}", TimeUtil.getTimeLeft(this.timeLeft()));
        this.player.sendMessage(message);
    }

    public void clear() {
        if (this.bukkitTask != null) {
            PlayerRepository playerRepository = this.plugin.getPlayerRepository();
            PlayerWrapper playerWrapper = playerRepository.getByPlayer(player);
            playerWrapper.removeCooldown(this);
            Bukkit.getScheduler().cancelTask(this.bukkitTask.getTaskId());
            this.bukkitTask = null;
        }
    }
}
