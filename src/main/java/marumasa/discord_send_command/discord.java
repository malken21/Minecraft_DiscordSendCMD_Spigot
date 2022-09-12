package marumasa.discord_send_command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class discord extends Thread {
    //----------Thread----------//

    private final String url;
    private final String json;
    private final Player player;
    private final Config con;
    private final String msg;

    public discord(final String webhook, final String message, final Player sender, final Config config) {
        player = sender;
        con = config;
        msg = message;

        url = webhook;
        json = String.format(
                "{\"content\":\"%s\",\"username\":\"%s\",\"avatar_url\":\"%s\"}",
                message,
                player.getDisplayName().replaceAll("§([0-9a-f]|r|l|o|n|m|k)", ""),
                String.format(config.avatarURL, player.getUniqueId())
        );
    }

    public void run() {
        request.post(url, json);
        player.sendMessage(String.format(ChatColor.YELLOW + con.resultText, ChatColor.GOLD + msg));
    }
}
