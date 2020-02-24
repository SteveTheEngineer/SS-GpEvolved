package me.ste.stevesseries.gpevolved;

import me.ste.stevesseries.gpevolved.player.GPEPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        GPEPlayer.players.put(e.getPlayer().getUniqueId(), new GPEPlayer(e.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        GPEPlayer.players.remove(e.getPlayer().getUniqueId());
    }
}