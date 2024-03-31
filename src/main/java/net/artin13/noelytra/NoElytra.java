package net.artin13.noelytra;

import org.bukkit.plugin.java.JavaPlugin;

public class NoElytra extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Balanced elytra succesfully loaded!");
        getServer().getPluginManager().registerEvents(new EventChecker(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Unloaded balanced elytra!");
    }

}
