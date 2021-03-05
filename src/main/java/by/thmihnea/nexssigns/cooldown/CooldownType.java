package by.thmihnea.nexssigns.cooldown;

import org.bukkit.Material;

public class CooldownType {

    private Material material;

    public CooldownType(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }
}
