package com.kiwifisher.noai;

import org.bukkit.plugin.java.JavaPlugin;

public class NoAI extends JavaPlugin {

    @Override
    public void onEnable() {

        /*
        Get the config and save it so that the listener can see which mobs it must disable AI for.
         */
        getConfig().options().copyDefaults(true);
        saveConfig();

        /*
        Register the listener
         */
        getServer().getPluginManager().registerEvents(new MobSpawnListener(this), this);

        /*
        Register the command
         */
        getCommand("noai").setExecutor(new CommandHandler(this));

    }

}
