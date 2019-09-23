package me.ste.stevesseries.gpevolved.player.disease;

import org.bukkit.entity.Player;

public abstract class Disease {
    private String name;
    public abstract void tick(Player p);
    public abstract void begin(Player p);
    public abstract void end(Player p);
}