package marumasa.discord_send_command;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    public final Map<String, String> channels = new HashMap<>();
    public final int EmbedColor;
    public final String errorText;
    public final String resultText;

    public Config(minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        final List<Map<?, ?>> items = config.getMapList("channels");
        for (Map<?, ?> item : items) {
            channels.put((String) item.get("name"), (String) item.get("webhook"));
        }
        EmbedColor = config.getInt("EmbedColor");
        errorText = config.getString("errorText");
        resultText = config.getString("resultText");
    }
}
