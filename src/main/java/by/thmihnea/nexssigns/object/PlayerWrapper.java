package by.thmihnea.nexssigns.object;

import by.thmihnea.nexssigns.cooldown.Cooldown;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PlayerWrapper {

    private Player player;
    private Set<Cooldown> cooldowns;

    public PlayerWrapper(Player player) {
        this.player = player;
        this.cooldowns = new HashSet<>();
    }

    public Set<Cooldown> getCooldowns() {
        return this.cooldowns;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Cooldown getCooldownByMaterial(Material material) {
        Optional<Cooldown> match = this.cooldowns.stream().filter(obj -> obj.getCooldownType().getMaterial().equals(material)).findFirst();
        return match.orElse(null);
    }

    public boolean hasCooldown(Material material) {
        return this.getCooldownByMaterial(material) != null;
    }

    public void addCooldown(Cooldown cooldown) {
        this.cooldowns.add(cooldown);
    }

    public void removeCooldown(Cooldown cooldown) {
        this.cooldowns.remove(cooldown);
    }
}
