package me.ste.stevesseries.gpevolved;

import me.ste.stevesseries.corebase.registry.Registry;
import me.ste.stevesseries.corebase.registry.RegistryManager;
import me.ste.stevesseries.corebase.registry.rule.ClassRule;
import me.ste.stevesseries.corebase.registry.rule.NotNullRule;
import me.ste.stevesseries.gpevolved.player.GPEPlayer;
import me.ste.stevesseries.gpevolved.player.disease.Disease;
import org.bukkit.NamespacedKey;
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
        Registry diseaseRegistry = RegistryManager.getInstance().addRegistry(new NamespacedKey(this, "disease"));
        diseaseRegistry.addRules(new NotNullRule(), new ClassRule(Disease.class));

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