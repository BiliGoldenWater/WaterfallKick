package indi.goldenwater.waterfallkick;

import indi.goldenwater.waterfallkick.listeners.OnServerKickEvent;
import indi.goldenwater.waterfallkick.utils.Config;
import net.md_5.bungee.api.plugin.Plugin;

public final class WaterfallKick extends Plugin {
    private static WaterfallKick instance;
    private Config config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        config = new Config(getDataFolder(), "config.json", getLogger(),
                "{\"lobbyServerName\": \"lobby\", " +
                        "\"disabledServer\":\"login\"}");

        getProxy().getPluginManager().registerListener(this, new OnServerKickEvent());

        getLogger().info("Enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        config.save();
        getLogger().info("Disabled.");
    }

    public static WaterfallKick getInstance() {
        return instance;
    }

    public Config getConfig() {
        return config;
    }
}
