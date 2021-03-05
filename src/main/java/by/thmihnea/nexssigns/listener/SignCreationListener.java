package by.thmihnea.nexssigns.listener;

import by.thmihnea.nexssigns.LangUtil;
import by.thmihnea.nexssigns.NexsSigns;
import by.thmihnea.nexssigns.NumberUtil;
import by.thmihnea.nexssigns.cache.SignRepository;
import by.thmihnea.nexssigns.file.SignManager;
import by.thmihnea.nexssigns.listener.annotation.Listen;
import by.thmihnea.nexssigns.object.SignWrapper;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.List;

@Listen
public class SignCreationListener implements Listener {

    private final NexsSigns plugin;
    private final SignRepository signRepository;
    private final SignManager signManager;

    public SignCreationListener(NexsSigns plugin) {
        this.plugin = plugin;
        this.signRepository = plugin.getSignRepository();
        this.signManager = plugin.getSignManager();
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        String firstLine = e.getLine(0);
        if (!(firstLine.equalsIgnoreCase("[SKYPVP]"))) return;

        String materialString = e.getLine(1);
        String amountString = e.getLine(2);
        String cooldownString = e.getLine(3);

        Material material;
        long cooldown = 0;

        if (NumberUtil.isInteger(materialString)) {
            int materialId = Integer.parseInt(materialString);
            material = Material.getMaterial(materialId);
        } else {
            material = Material.valueOf(materialString.toUpperCase());
        }
        if (material == null) return;

        if (!(NumberUtil.isInteger(amountString))) return;
        int amount = Integer.parseInt(amountString);

        if (NumberUtil.isInteger(cooldownString)) cooldown = Long.parseLong(cooldownString);

        SignWrapper signWrapper = new SignWrapper((Sign) e.getBlock().getState(), material, amount, cooldown);

        List<String> stringList = this.plugin.getConfigManager().getStringList("sign-format");
        stringList = LangUtil.applyAllPlaceholders(stringList, material, amount);

        for (int i = 0; i < stringList.size(); i++) {
            e.setLine(i, stringList.get(i));
        }

        this.signRepository.addEntry(signWrapper);
        this.signManager.saveSignWrapper(signWrapper);
    }
}
