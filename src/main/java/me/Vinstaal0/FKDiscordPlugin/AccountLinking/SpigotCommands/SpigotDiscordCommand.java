package me.Vinstaal0.FKDiscordPlugin.AccountLinking.SpigotCommands;

import me.Vinstaal0.FKDiscordPlugin.AccountLinking.AccountManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Vinstaal0 on 11-4-2018.
 */
public class SpigotDiscordCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        String token = AccountManager.registerUser(player.getUniqueId());

        player.sendMessage(ChatColor.GRAY + "Your token is " + token);
        player.sendMessage(ChatColor.GRAY + "Issue the command /Discord " + ChatColor.WHITE +  token
                + ChatColor.GRAY + " on Discord");

        return true;
    }

}
