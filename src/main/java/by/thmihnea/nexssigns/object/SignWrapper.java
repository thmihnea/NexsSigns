package by.thmihnea.nexssigns.object;

import by.thmihnea.nexssigns.LangUtil;
import by.thmihnea.nexssigns.NexsSigns;
import by.thmihnea.nexssigns.file.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;

import java.util.List;
import java.util.UUID;

public class SignWrapper {

    private Sign sign;
    private Material material;
    private UUID uuid;
    private int amount;
    private long cooldown;

    public SignWrapper(Sign sign, Material material, int amount, long cooldown) {
        this.sign = sign;
        this.material = material;
        this.amount = amount;
        this.cooldown = cooldown;
    }

    public SignWrapper(Sign sign, Material material, int amount) {
        this(sign, material, amount, 0);
    }

    public Sign getSign() {
        return this.sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Location getLocation() {
        return this.sign.getLocation();
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getCooldown() {
        return this.cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    
    @Override
    public String toString() {
        return "{SignWrapper@" + this.hashCode() + " | Location: " +
                this.getLocation().getBlockX() + " " + this.getLocation().getBlockY() + " " + this.getLocation().getBlockZ() +
                " | Material: " + this.material.toString() +
                " | Amount: " + this.amount + "}";
    }

    public void display(NexsSigns plugin) {
        ConfigManager configManager = plugin.getConfigManager();
        List<String> stringList = configManager.getStringList("sign-format");
        stringList = LangUtil.applyAllPlaceholders(stringList, this.material, this.amount);
        for (int i = 0; i < stringList.size(); i++) {
            this.sign.setLine(i, stringList.get(i));
        }
        this.sign.update();
    }
}
