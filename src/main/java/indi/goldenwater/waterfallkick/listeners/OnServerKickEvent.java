package indi.goldenwater.waterfallkick.listeners;

import indi.goldenwater.waterfallkick.WaterfallKick;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnServerKickEvent implements Listener {
    @EventHandler
    public void onServerKickEvent(ServerKickEvent event) {
        WaterfallKick plugin = WaterfallKick.getInstance();
        final ProxiedPlayer player = event.getPlayer();

        String lobbyName = plugin.getConfig().getConfig().get("lobbyServerName");
        String disabledServerName = plugin.getConfig().getConfig().get("disabledServer");
        String playerServerName = player.getServer().getInfo().getName();

        if (!(playerServerName.equals(lobbyName) ||
                playerServerName.equals(disabledServerName) ||
                event.getKickReasonComponent()[0].toPlainText().equals("[Proxy] Lost connection to server."))) {
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(lobbyName);
            event.setCancelled(true);
            event.setCancelServer(serverInfo);
            player.sendMessage(event.getKickReasonComponent());
        }
    }
}
