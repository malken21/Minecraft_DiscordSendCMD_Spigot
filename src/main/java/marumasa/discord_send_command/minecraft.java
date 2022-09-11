package marumasa.discord_send_command;

import marumasa.discord_send_command.cmd.notice;
import org.bukkit.plugin.java.JavaPlugin;

public final class minecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        Config config = new Config(this);
        getCommand("notice").setExecutor(new notice(config));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
