package indi.goldenwater.waterfallkick.listeners;

import indi.goldenwater.waterfallkick.WaterfallKick;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnServerKickEvent implements Listener {
    @EventHandler
    public void onServerKickEvent(ServerKickEvent event) {
        final WaterfallKick plugin = WaterfallKick.getInstance();
        final ProxiedPlayer player = event.getPlayer();

        final String lobbyName = plugin.getConfig().getConfig().get("lobbyServerName");
        final String disabledServerName = plugin.getConfig().getConfig().get("disabledServer");
        final Server playerServer = player.getServer();
        if (playerServer == null) return;
        final ServerInfo playerServerInfo = playerServer.getInfo();
        if (playerServerInfo == null) return;
        final String playerServerName = playerServerInfo.getName();

        if (!(playerServerName.equals(lobbyName) ||
                playerServerName.equals(disabledServerName) ||
                event.getKickReasonComponent()[0].toPlainText().equals("[Proxy] Lost connection to server."))) {
            final ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(lobbyName);
            event.setCancelled(true);
            event.setCancelServer(serverInfo);
            player.sendMessage(event.getKickReasonComponent());
        }
    }
}
