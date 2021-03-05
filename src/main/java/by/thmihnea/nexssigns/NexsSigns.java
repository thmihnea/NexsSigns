package by.thmihnea.nexssigns;

import by.thmihnea.nexssigns.cache.PlayerRepository;
import by.thmihnea.nexssigns.cache.SignRepository;
import by.thmihnea.nexssigns.file.ConfigManager;
import by.thmihnea.nexssigns.file.FileManager;
import by.thmihnea.nexssigns.file.SignManager;
import by.thmihnea.nexssigns.listener.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NexsSigns extends JavaPlugin {

    private SignRepository signRepository;
    private ListenerManager listenerManager;
    private FileManager fileManager;
    private ConfigManager configManager;
    private SignManager signManager;
    private PlayerRepository playerRepository;

    @Override
    public void onEnable() {
        long stopWatch = System.currentTimeMillis();

        this.fileManager = new FileManager(this);
        this.configManager = new ConfigManager(this);
        this.signRepository = new SignRepository(this);
        this.signManager = new SignManager(this);
        this.playerRepository = new PlayerRepository(this);
        this.listenerManager = new ListenerManager(this);

        LoggerUtil.logInfo("All modules have been enabled. Process took: " + (System.currentTimeMillis() - stopWatch) + "ms.");
    }

    @Override
    public void onDisable() {
        LoggerUtil.logInfo("Disabling plugin modules.");
    }

    public SignRepository getSignRepository() {
        return this.signRepository;
    }

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public SignManager getSignManager() {
        return this.signManager;
    }

    public PlayerRepository getPlayerRepository() {
        return this.playerRepository;
    }
}
