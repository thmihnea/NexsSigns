package by.thmihnea.nexssigns;

import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.logging.Level;

public class LoggerUtil {

    public static void logInfo(String... message) {
        String s = Arrays.toString(message);
        Bukkit.getLogger().log(Level.INFO, "[NexsSigns]" + " " + s.replace(s.substring(0, 1), "").replace(s.substring(s.length() - 1), ""));
    }

    public static void logSevere(String... message) {
        String s = Arrays.toString(message);
        Bukkit.getLogger().log(Level.INFO, "[NexsSigns]" + " " + s.replace(s.substring(0, 1), "").replace(s.substring(s.length() - 1), ""));
    }
}
