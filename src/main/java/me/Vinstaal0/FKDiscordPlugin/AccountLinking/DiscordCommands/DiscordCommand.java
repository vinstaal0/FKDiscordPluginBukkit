package me.Vinstaal0.FKDiscordPlugin.AccountLinking.DiscordCommands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import me.Vinstaal0.FKDiscordPlugin.AccountLinking.AccountManager;
import org.javacord.api.entity.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vinstaal0 on 11-4-2018.
 */
public class DiscordCommand implements CommandExecutor {

    public String token;
    public static List<String> acceptedUUID = new ArrayList<String>();

    public static HashMap<String, String> linkedAccounts = new HashMap<String, String>();

    @Command(aliases = {"!discord", "/discord"}, description = "The command to link your Minecraft account to your Discord account"
            + " for the FallingKingdom server")
    public void onCommand(User user, String[] args) {

        if (args.length == 0)
            user.sendMessage("Please enter the command /discord in game to receive a token, then issue this command in discord again with the token (!discord [token]");

        if (args.length == 1 && args[0].length() == 5) {
            this.token = args[0];

            AccountManager.linkUser(user, token);
        }
    }
}
