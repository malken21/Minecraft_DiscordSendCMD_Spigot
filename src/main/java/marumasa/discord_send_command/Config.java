package marumasa.discord_send_command;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    public final Map<String, String> channels = new HashMap<>();
    public final String errorText;
    public final String resultText;
    public final String avatarURL;

    public Config(minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        final List<Map<?, ?>> items = config.getMapList("channels");
        for (Map<?, ?> item : items) {
            channels.put((String) item.get("name"), (String) item.get("webhook"));
        }
        errorText = config.getString("errorText");
        resultText = config.getString("resultText");
        avatarURL = config.getString("avatarURL");
    }
}
