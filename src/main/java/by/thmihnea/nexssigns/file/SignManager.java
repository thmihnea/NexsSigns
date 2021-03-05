package by.thmihnea.nexssigns.file;

import by.thmihnea.nexssigns.NexsSigns;
import by.thmihnea.nexssigns.cache.SignRepository;
import by.thmihnea.nexssigns.object.SignWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SignManager {

    private final NexsSigns plugin;
    private final FileManager fileManager;
    private final SignRepository signRepository;
    private final File signsFile;

    public SignManager(NexsSigns plugin) {
        this.plugin = plugin;
        this.fileManager = plugin.getFileManager();
        this.signRepository = plugin.getSignRepository();
        this.signsFile = this.initializeManagerFile();
        this.initializeSignWrappers();
    }

    public void initializeSignWrappers() {
        FileConfiguration fileConfiguration = this.fileManager.load(this.signsFile);
        if (fileConfiguration.getConfigurationSection("signs") == null) return;
        fileConfiguration.getConfigurationSection("signs").getKeys(false).forEach(key -> {
            String path = "signs." + key;
            SignWrapper signWrapper = this.loadSignWrapper(path);
            if (signWrapper == null) {
                fileConfiguration.set(path, null);
                try {
                    fileConfiguration.save(this.signsFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            signWrapper.setUuid(UUID.fromString(key));
            signWrapper.display(this.plugin);
            this.signRepository.addEntry(signWrapper);
        });
    }

    public void saveSignWrapper(SignWrapper signWrapper) {
        Location location = signWrapper.getLocation();
        String worldName = location.getWorld().getName();
        int LOC_X = location.getBlockX(); int LOC_Y = location.getBlockY(); int LOC_Z = location.getBlockZ();
        String RANDOM_ID = UUID.randomUUID().toString();
        signWrapper.setUuid(UUID.fromString(RANDOM_ID));
        String materialName = signWrapper.getMaterial().name();
        int amount = signWrapper.getAmount();
        long cooldown = signWrapper.getCooldown();

        String path = "signs." + RANDOM_ID + ".";
        FileConfiguration fileConfiguration = this.fileManager.load(this.signsFile);

        fileConfiguration.set(path + "x", LOC_X);
        fileConfiguration.set(path + "y", LOC_Y);
        fileConfiguration.set(path + "z", LOC_Z);
        fileConfiguration.set(path + "world", worldName);
        fileConfiguration.set(path + "material", materialName);
        fileConfiguration.set(path + "amount", amount);
        fileConfiguration.set(path + "cooldown", cooldown);

        try {
            fileConfiguration.save(this.signsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SignWrapper loadSignWrapper(String path) {
        FileConfiguration fileConfiguration = this.fileManager.load(this.signsFile);
        int LOC_X = fileConfiguration.getInt(path + ".x");
        int LOC_Y = fileConfiguration.getInt(path + ".y");
        int LOC_Z = fileConfiguration.getInt(path + ".z");
        World world = Bukkit.getWorld(fileConfiguration.getString(path + ".world"));
        Material material = Material.valueOf(fileConfiguration.getString(path + ".material"));
        int amount = fileConfiguration.getInt(path + ".amount");
        long cooldown = fileConfiguration.getLong(path + ".cooldown");

        Location signLocation = new Location(world, LOC_X, LOC_Y, LOC_Z);
        Block block = signLocation.getBlock();
        if (!(block.getType().toString().toUpperCase().contains("SIGN"))) return null;
        return new SignWrapper((Sign) world.getBlockAt(signLocation).getState(), material, amount, cooldown);
    }

    public File initializeManagerFile(){
        File file = new File(this.plugin.getDataFolder(), "signs.yml");
        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }
}
