package me.ste.stevesseries.gpevolved;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.ste.stevesseries.gpevolved.player.GPEPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SlimefunIntegration {
    private GpEvolved plugin;
    public SlimefunAddon addon;
    public Category category;
    public SlimefunItem thermometer;

    public SlimefunIntegration(GpEvolved plugin) {
        this.plugin = plugin;
        addon = new SlimefunAddon() {
            @Override
            public JavaPlugin getJavaPlugin() {
                return plugin;
            }

            @Override
            public String getBugTrackerURL() {
                return "https://github.com/SteveTheEngineer/SS-GpEvolved/issues";
            }
        };
        category = new Category(new NamespacedKey(plugin, "sfitems"), new CustomItem(Material.DIRT, "&rSteve's Series | Gameplay Evolved"));

        thermometer = new SlimefunItem(category, new SlimefunItemStack("SS_GPEVOLVED_THERMOMETER", Material.BLAZE_ROD, "&rThermometer"), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.REDSTONE), new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.REDSTONE), new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.REDSTONE), new ItemStack(Material.IRON_INGOT)
        });
        thermometer.addItemHandler((ItemUseHandler) e -> {
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&oTemperature at your location is: " + Temperature.getTemperatureAt(e.getPlayer().getLocation())));
        });
        thermometer.register(addon);
    }
}