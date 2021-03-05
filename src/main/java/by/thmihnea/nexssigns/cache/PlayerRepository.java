package by.thmihnea.nexssigns.cache;

import by.thmihnea.nexssigns.NexsSigns;
import by.thmihnea.nexssigns.object.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PlayerRepository {

    private final NexsSigns plugin;
    private final Set<PlayerWrapper> cache;

    public PlayerRepository(NexsSigns plugin) {
        this.plugin = plugin;
        this.cache = new HashSet<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            PlayerWrapper playerWrapper = new PlayerWrapper(player);
            this.addPlayer(playerWrapper);
        });
    }

    public void addPlayer(PlayerWrapper playerWrapper) {
        this.cache.add(playerWrapper);
    }

    public void removePlayer(Player player) {
        PlayerWrapper playerWrapper = this.getByPlayer(player);
        this.cache.remove(playerWrapper);
    }

    public PlayerWrapper getByPlayer(Player player) {
        Optional<PlayerWrapper> match = this.cache.stream().filter(obj -> obj.getPlayer().equals(player)).findFirst();
        return match.orElse(null);
    }

    public boolean contains(Player player) {
        return this.getByPlayer(player) != null;
    }

    public Set<PlayerWrapper> getCache() {
        return this.cache;
    }
}
