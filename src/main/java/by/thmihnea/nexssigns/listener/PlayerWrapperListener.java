package by.thmihnea.nexssigns.listener;

import by.thmihnea.nexssigns.NexsSigns;
import by.thmihnea.nexssigns.cache.PlayerRepository;
import by.thmihnea.nexssigns.listener.annotation.Listen;
import by.thmihnea.nexssigns.object.PlayerWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@Listen
public class PlayerWrapperListener implements Listener {

    private final NexsSigns plugin;
    private final PlayerRepository playerRepository;

    public PlayerWrapperListener(NexsSigns plugin) {
        this.plugin = plugin;
        this.playerRepository = plugin.getPlayerRepository();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerWrapper playerWrapper = new PlayerWrapper(player);
        this.playerRepository.addPlayer(playerWrapper);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        this.playerRepository.removePlayer(player);
    }
}
