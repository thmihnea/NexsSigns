package by.thmihnea.nexssigns.listener;

import by.thmihnea.nexssigns.NexsSigns;
import by.thmihnea.nexssigns.cache.SignRepository;
import by.thmihnea.nexssigns.listener.annotation.Listen;
import by.thmihnea.nexssigns.object.SignWrapper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

@Listen
public class SignInteractionListener implements Listener {

    private final NexsSigns plugin;
    private final SignRepository signRepository;

    public SignInteractionListener(NexsSigns plugin) {
        this.plugin = plugin;
        this.signRepository = plugin.getSignRepository();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        if (block == null || block.getType() == Material.AIR) return;
        if (!(block.getType().toString().toUpperCase().contains("SIGN"))) return;
        Sign sign = (Sign) block.getState();
        if (!(this.signRepository.contains(sign))) return;
        SignWrapper signWrapper = this.signRepository.getFromSign(sign);
        ItemStack itemStack = new ItemStack(signWrapper.getMaterial(), signWrapper.getAmount());
        Player player = e.getPlayer();
        player.getInventory().addItem(itemStack);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block == null || block.getType() == Material.AIR) return;
        if (!(block.getType().toString().toUpperCase().contains("SIGN"))) return;
        Sign sign = (Sign) block.getState();
        if (!(this.signRepository.contains(sign))) return;
        SignWrapper signWrapper = this.signRepository.getFromSign(sign);
        this.signRepository.removeEntry(signWrapper);
        File file = new File(this.plugin.getDataFolder(), "signs.yml");
        FileConfiguration fileConfiguration = this.plugin.getFileManager().load(file);
        fileConfiguration.set("signs." + signWrapper.getUuid().toString(), null);
        try {
            fileConfiguration.save(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
