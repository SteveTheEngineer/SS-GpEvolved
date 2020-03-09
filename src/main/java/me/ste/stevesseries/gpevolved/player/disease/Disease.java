package me.ste.stevesseries.gpevolved.player.disease;

import org.bukkit.entity.Player;

public interface Disease {
    void begin(Player player, long duration);
    void end(Player player, long duration);
    boolean isTickable();
    void tick(Player player, long duration, long lastedFor);
}