package by.thmihnea.nexssigns.listener;

import by.thmihnea.nexssigns.NexsSigns;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class ListenerManager {

    private final NexsSigns plugin;

    public ListenerManager(NexsSigns plugin) {
        this.plugin = plugin;
        this.register();
    }

    public void register() {
        List<Class<? extends Listener>> listeners = Arrays.asList(SignCreationListener.class, SignInteractionListener.class);

        listeners.forEach(listener -> {
            try {
                Listener instance = listener
                        .getDeclaredConstructor(NexsSigns.class)
                        .newInstance(this.plugin);
                Bukkit.getPluginManager().registerEvents(instance, this.plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }
}
