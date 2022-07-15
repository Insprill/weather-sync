package net.insprill.weathersync.weather;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.insprill.fetch4j.Response;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static net.insprill.fetch4j.Fetch.fetch;

public class WeatherService {

    private static final String API_URL = "https://api.weatherapi.com/v1/current.json?key=%s&q=%s";

    private final Plugin plugin;
    private final String apiKey;

    public WeatherService(JavaPlugin plugin, String apiKey) {
        this.plugin = plugin;
        this.apiKey = apiKey;
    }


    /**
     * Fetches the weather from a location and updates the given world.
     *
     * @param world    The world to update.
     * @param location The location to get the weather information from.
     */
    public void fetchAndUpdate(World world, String location) {
        try {
            WeatherCode weatherCode = fetchWeatherCode(location);
            WeatherStatus status = weatherCode.getStatus();
            world.setStorm(status == WeatherStatus.STORM);
            world.setThundering(status == WeatherStatus.RAIN);
            world.setWeatherDuration(status == WeatherStatus.CLEAR ? 0 : Integer.MAX_VALUE);
        } catch (Exception e) {
            plugin.getLogger().severe("Failed to update weather: " + e.getMessage());
        }
    }

    /**
     * Fetches the weather code from a specific location.
     *
     * @param location The location to fetch the weather code from.
     * @return The {@link WeatherCode}.
     */
    private WeatherCode fetchWeatherCode(String location) {
        Response response = fetch(String.format(API_URL, this.apiKey, location));
        JsonObject json = new JsonParser().parse(response.getBody()).getAsJsonObject();

        Preconditions.checkState(response.ok(), json.get("error").getAsJsonObject().get("message").getAsString());

        JsonObject current = json.get("current").getAsJsonObject();
        JsonObject condition = current.get("condition").getAsJsonObject();
        return WeatherCode.get(condition.get("code").getAsInt());
    }

}
