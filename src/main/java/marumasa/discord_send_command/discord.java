package marumasa.discord_send_command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class discord extends Thread {
    //----------Thread----------//

    private final String url;
    private final String json;
    private final Player player;

    public discord(final String webhook, final String[] args, final Player sender, final Config config) {
        final int size = args.length;

        final StringBuilder message = new StringBuilder();
        for (int loop = 1; loop < size; loop++) {
            if (loop != 1) message.append(" ");
            message.append(args[loop]);
        }
        if (message.length() == 0) sender.sendMessage(ChatColor.RED + config.errorText);

        url = webhook;
        json = String.format(
                "{\"embeds\": [{\"title\": \"%s\",\"description\": \"%s\",\"color\": %d}]}",
                sender.getDisplayName().replaceAll("§([0-9a-f]|r|l|o|n|m|k)", ""),
                message,
                config.EmbedColor
        );
        player = sender;
    }

    public void run() {
        request.post(url, json);
        player.sendMessage("メッセージを送信しました");
    }
}
