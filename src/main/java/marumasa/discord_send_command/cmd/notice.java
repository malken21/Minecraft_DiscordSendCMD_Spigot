package marumasa.discord_send_command.cmd;

import marumasa.discord_send_command.Config;
import marumasa.discord_send_command.discord;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class notice implements CommandExecutor, TabCompleter {
    private final Config con;
    private final List<String> tab;

    public notice(Config config) {
        con = config;
        tab = new ArrayList<>(config.channels.keySet());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        final String webhook = con.channels.get(args[0]);
        if (webhook == null) {
            player.sendMessage(ChatColor.RED + con.errorText);
            return false;
        }

        final int size = args.length;

        final StringBuilder message = new StringBuilder();
        for (int loop = 1; loop < size; loop++) {
            if (loop != 1) message.append(" ");
            message.append(args[loop]);
        }
        if (message.length() == 0) {
            player.sendMessage(ChatColor.RED + con.errorText);
            return false;
        }

        new discord(webhook, message.toString(), player, con).start();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return tab;
        } else {
            return null;
        }
    }
}
