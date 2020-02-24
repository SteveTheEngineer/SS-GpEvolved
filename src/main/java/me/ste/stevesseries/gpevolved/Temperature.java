package me.ste.stevesseries.gpevolved;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Biome;

public class Temperature {
    public static double getBiomeTemperature(Biome b) {
        return GpEvolved.INSTANCE.getSettings().getBiomeTemperature(b);
    }
    public static double getHeightMultiplier(World w, double height) {
        double a = 1 - height / (w.getMaxHeight() / 2);
        return a + (a > 0 ? 1 : -1);
    }
    public static double getTimeMultiplier(long time) {
        double a = 1 - time / 10000;
        return a + (a > 0 ? 1 : -1);
    }
    public static double getTemperatureAt(Location l) {
        return getBiomeTemperature(l.getBlock().getBiome()) * getHeightMultiplier(l.getWorld(), l.getY()) * getTimeMultiplier(l.getWorld().getTime());
    }
}