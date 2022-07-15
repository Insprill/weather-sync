package net.insprill.weathersync;

import net.insprill.weathersync.weather.WeatherService;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class WeatherSync extends JavaPlugin {

    private static final int MIN_UPDATE_RATE = 1;

    private WeatherService weatherService;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.weatherService = new WeatherService(this, getConfig().getString("api-key"));

        long updateRate = getConfig().getLong("update-rate") * 20L * 60L;
        updateRate = Math.max(MIN_UPDATE_RATE, updateRate);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::updateAllWeather, 0L, updateRate);
    }

    /**
     * Updates the weather on all configured worlds.
     */
    private void updateAllWeather() {
        for (String worldName : getConfig().getConfigurationSection("worlds").getKeys(false)) {
            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                getLogger().log(Level.SEVERE, "Failed to find world by name {}!", worldName);
                continue;
            }
            String location = getConfig().getString("worlds." + worldName + ".location");
            this.weatherService.fetchAndUpdate(world, location);
        }
    }

}
