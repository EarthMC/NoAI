package com.kiwifisher.noai;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    private NoAI plugin;

    public CommandHandler(NoAI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        /*
        If a player types /noai reload, and they have the permisson noai.reload, then relaod the config and notify them that it has been done.
         */
        if (command.getName().equalsIgnoreCase("noai")) {

            if (commandSender instanceof Player) {

                Player player = (Player) commandSender;

                if (player.hasPermission("noai.reload")) {

                    plugin.reloadConfig();
                    player.sendMessage(ChatColor.YELLOW + "Reloaded the NoAI config!");

                }

            }

        }

        return false;
    }
}
