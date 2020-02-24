package me.ste.stevesseries.gpevolved;

import com.google.common.collect.Sets;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    private GpEvolved plugin;
    private Map<Biome, Double> biomeTemperatures = new HashMap<>();
    private double playerTemperature = 0;

    public Settings(GpEvolved plugin) {
        this.plugin = plugin;
    }

    public double getBiomeTemperature(Biome biome) {
        return biomeTemperatures.get(biome);
    }

    public double getPlayerTemperature() {
        return playerTemperature;
    }

    public void load() {
        biomeTemperatures.clear();

        ConfigurationSection config = plugin.getConfig();
        ConfigurationSection temperature = config.getConfigurationSection("temperature");
        ConfigurationSection tBiome = temperature.getConfigurationSection("biome");
        double tBiomeDefault = 20;

        if(tBiome.contains("default")) {
            tBiomeDefault = tBiome.getDouble("default");
        } else {
            plugin.getLogger().warning("Cannot find default biome temperature in the configuration, using " + tBiomeDefault + " as default");
        }
        double finalTBiomeDefault = tBiomeDefault;
        Sets.newHashSet(Biome.values()).forEach(biome -> {
            if(tBiome.contains(biome.name())) {
                biomeTemperatures.put(biome, tBiome.getDouble(biome.name()));
            } else {
                biomeTemperatures.put(biome, finalTBiomeDefault);
                plugin.getLogger().warning("Cannot find temperature for biome " + biome.name() + ", using default value instead (" + finalTBiomeDefault + "). Please check your configuration");
            }
        });

        playerTemperature = temperature.getDouble("player");
    }
}