package com.karlofduty.NoAI;

import org.bukkit.plugin.java.JavaPlugin;

public class NoAI extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new MobSpawnListener(this), this);
        getCommand("noai").setExecutor(new ReloadCommand(this));
    }
}
