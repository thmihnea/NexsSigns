package by.thmihnea.nexssigns.file;

import by.thmihnea.nexssigns.LoggerUtil;
import by.thmihnea.nexssigns.NexsSigns;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.List;

public class ConfigManager {

    private final NexsSigns plugin;
    private final FileManager fileManager;
    private File configFile;
    private FileConfiguration config;

    public ConfigManager(NexsSigns plugin) {
        this.plugin = plugin;
        this.fileManager = plugin.getFileManager();
        this.initialize();
    }

    public void initialize() {
        this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        File parent = this.configFile.getParentFile();
        if (!(parent.exists())) parent.mkdirs();
        if (!(this.configFile.exists())) this.plugin.saveDefaultConfig();
        this.config = this.fileManager.load(this.configFile);
        LoggerUtil.logInfo("Loaded configuration assets.");
    }

    public void reload() {
        this.plugin.saveConfig();
        this.plugin.reloadConfig();
        this.config = this.fileManager.load(this.configFile);
    }

    public Object get(String path) {
        return this.config.get(path);
    }

    public List<String> getStringList(String path) {
        return this.config.getStringList(path);
    }

    public boolean getBoolean(String path) {
        return (boolean) this.get(path);
    }

    public int getInt(String path) {
        return (int) this.get(path);
    }

    public String getString(String path) {
        return (String) this.get(path);
    }

    public double getDouble(String path) {
        return (double) this.get(path);
    }

    public float getFloat(String path) {
        return (float) this.get(path);
    }
}
