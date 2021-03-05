package by.thmihnea.nexssigns.file;

import by.thmihnea.nexssigns.LoggerUtil;
import by.thmihnea.nexssigns.NexsSigns;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private final NexsSigns plugin;

    public FileManager(NexsSigns plugin) {
        this.plugin = plugin;
        LoggerUtil.logInfo("Instantiating file assets.");
    }

    public boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    public File createFile(String path) throws IOException {
        File file = new File(path);
        boolean created = file.createNewFile();
        if (created) return file;
        return null;
    }

    public File createDirectory(String path) {
        File file = new File(path);
        boolean created = file.mkdirs();
        if (created) return file;
        return null;
    }

    public File getFile(String path) {
        return new File(path);
    }

    public FileConfiguration load(File file) {
        if (!(file.isFile())) return null;
        return YamlConfiguration.loadConfiguration(file);
    }
}
