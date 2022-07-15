package net.insprill.weathersync.weather;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.insprill.fetch4j.Response;
import org.junit.jupiter.api.Test;

import static net.insprill.fetch4j.Fetch.fetch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherCodeTest {

    @Test
    void containsAllCodes() {
        Response response = fetch("https://www.weatherapi.com/docs/weather_conditions.json");
        assertTrue(response.ok());
        for (JsonElement jsonElement : new JsonParser().parse(response.getBody()).getAsJsonArray()) {
            JsonObject obj = jsonElement.getAsJsonObject();
            int code = obj.get("code").getAsInt();
            assertNotNull(WeatherCode.get(code), "Failed to find WeatherCode mapping for code " + code);
        }
    }

}
