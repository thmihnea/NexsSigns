package by.thmihnea.nexssigns.cache;

import by.thmihnea.nexssigns.LoggerUtil;
import by.thmihnea.nexssigns.NexsSigns;
import by.thmihnea.nexssigns.object.SignWrapper;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SignRepository {

    private final NexsSigns plugin;
    private final Set<SignWrapper> cache;

    public SignRepository(NexsSigns plugin) {
        this.plugin = plugin;
        this.cache = new HashSet<>();
        LoggerUtil.logInfo("Instantiated the Sign Repository.");
    }

    public void addEntry(SignWrapper signWrapper) {
        this.cache.add(signWrapper);
    }

    public void removeEntry(SignWrapper signWrapper) {
        this.cache.remove(signWrapper);
    }

    public boolean contains(Sign sign) {
        Optional<SignWrapper> match = this.cache.stream().filter(obj -> obj.getSign().equals(sign)).findFirst();
        return match.isPresent();
    }

    public boolean contains(Location location) {
        Optional<SignWrapper> match = this.cache.stream().filter(obj -> obj.getLocation().equals(location)).findFirst();
        return match.isPresent();
    }

    public SignWrapper getFromSign(Sign sign) {
        Optional<SignWrapper> match = this.cache.stream().filter(obj -> obj.getSign().equals(sign)).findFirst();
        return match.orElse(null);
    }

    public SignWrapper getFromLocation(Location location) {
        Optional<SignWrapper> match = this.cache.stream().filter(obj -> obj.getLocation().equals(location)).findFirst();
        return match.orElse(null);
    }

    public Set<SignWrapper> getCache() {
        return this.cache;
    }
}
