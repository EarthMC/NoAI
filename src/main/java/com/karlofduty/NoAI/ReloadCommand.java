package com.karlofduty.NoAI;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor
{
    private NoAI plugin;
    public ReloadCommand(NoAI plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args)
    {
        if(args[0].equals("reload"))
        {
            if (commandSender.hasPermission("noai.reload"))
            {
                plugin.reloadConfig();
                commandSender.sendMessage(ChatColor.YELLOW + "Reloaded the NoAI config!");
            }
            else
            {
                commandSender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
            }
            return false;
        }
        return true;
    }
}
