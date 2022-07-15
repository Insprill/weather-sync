package net.insprill.weathersync.weather;

import org.jetbrains.annotations.Nullable;

public enum WeatherCode {
    W_1000(WeatherStatus.CLEAR),
    W_1003(WeatherStatus.CLEAR),
    W_1006(WeatherStatus.CLEAR),
    W_1009(WeatherStatus.CLEAR),
    W_1030(WeatherStatus.CLEAR),
    W_1063(WeatherStatus.CLEAR),
    W_1066(WeatherStatus.CLEAR),
    W_1069(WeatherStatus.CLEAR),
    W_1072(WeatherStatus.CLEAR),
    W_1087(WeatherStatus.CLEAR),
    W_1114(WeatherStatus.RAIN),
    W_1117(WeatherStatus.RAIN),
    W_1135(WeatherStatus.CLEAR),
    W_1147(WeatherStatus.CLEAR),
    W_1150(WeatherStatus.RAIN),
    W_1153(WeatherStatus.RAIN),
    W_1168(WeatherStatus.RAIN),
    W_1171(WeatherStatus.RAIN),
    W_1180(WeatherStatus.RAIN),
    W_1183(WeatherStatus.RAIN),
    W_1186(WeatherStatus.RAIN),
    W_1189(WeatherStatus.RAIN),
    W_1192(WeatherStatus.RAIN),
    W_1195(WeatherStatus.STORM),
    W_1198(WeatherStatus.RAIN),
    W_1201(WeatherStatus.RAIN),
    W_1204(WeatherStatus.RAIN),
    W_1207(WeatherStatus.RAIN),
    W_1210(WeatherStatus.RAIN),
    W_1213(WeatherStatus.RAIN),
    W_1216(WeatherStatus.RAIN),
    W_1219(WeatherStatus.RAIN),
    W_1222(WeatherStatus.RAIN),
    W_1225(WeatherStatus.STORM),
    W_1237(WeatherStatus.RAIN),
    W_1240(WeatherStatus.RAIN),
    W_1243(WeatherStatus.RAIN),
    W_1246(WeatherStatus.STORM),
    W_1249(WeatherStatus.RAIN),
    W_1252(WeatherStatus.RAIN),
    W_1255(WeatherStatus.RAIN),
    W_1258(WeatherStatus.RAIN),
    W_1261(WeatherStatus.RAIN),
    W_1264(WeatherStatus.RAIN),
    W_1273(WeatherStatus.STORM),
    W_1276(WeatherStatus.STORM),
    W_1279(WeatherStatus.STORM),
    W_1282(WeatherStatus.STORM),
    ;

    private final WeatherStatus status;

    WeatherCode(WeatherStatus status) {
        this.status = status;
    }

    /**
     * Gets the {@link WeatherStatus} associated with this WeatherCode.
     *
     * @return The WeatherStatus.
     */
    public WeatherStatus getStatus() {
        return this.status;
    }

    /**
     * Gets a {@link WeatherCode} from a <a href="https://www.weatherapi.com/">weatherapi.com</a> condition code.
     *
     * @param code The code to get.
     * @return The matching WeatherCode, or null if none was found.
     */
    @Nullable
    public static WeatherCode get(int code) {
        try {
            return WeatherCode.valueOf("W_" + code);
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

}
