package me.ste.stevesseries.gpevolved;

import me.ste.stevesseries.gpevolved.player.GPEPlayer;
import org.bukkit.block.Biome;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class GpEvolved extends JavaPlugin {
    public static GpEvolved INSTANCE;
    private Settings settings;
    private SlimefunIntegration slimefunIntegration = null;

    public GpEvolved() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        if(!new File(getDataFolder(), "config.yml").isFile()) {
            saveDefaultConfig();
        }
        settings = new Settings(this);
        if(getServer().getPluginManager().isPluginEnabled("Slimefun")) {
            this.slimefunIntegration = new SlimefunIntegration(this);
        } else {
            getLogger().warning("It's highly recommended to install Slimefun, as this plugin integrates with it and adds several items");
        }

        getServer().getOnlinePlayers().forEach(p -> GPEPlayer.players.put(p.getUniqueId(), new GPEPlayer(p)));
    }

    public Settings getSettings() {
        return settings;
    }

    @Override
    public void onDisable() {
        GPEPlayer.players.clear();
    }
}