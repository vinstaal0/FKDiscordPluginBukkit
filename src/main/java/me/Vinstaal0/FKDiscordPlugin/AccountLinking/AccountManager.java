package me.Vinstaal0.FKDiscordPlugin.AccountLinking;

import me.Vinstaal0.FKDiscordPlugin.Constants;
import me.Vinstaal0.FKDiscordPlugin.DiscordBot;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.javacord.api.entity.user.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Vinstaal0 on 11-4-2018.
 */
public class AccountManager {

    /**
     * Token generated from Minecraft
     *
     * @param String the generated token
     * @param UUID the players MC uuid
     */
    private static HashMap<String, UUID> UUIDToken = new HashMap<String, UUID>();

    /**
     * UUID from MC and Discord ID
     *
     * @param long player Discord ID
     * @param UUID the players MC uuid
     */
    private static HashMap<Long, UUID> linkedAccounts = new HashMap<Long, UUID>();

    /**
     * registers the MC user
     *
     * @param uuid the players MC uuid
     * @return the players token
     */
    public static String registerUser(UUID uuid) {

        String token = new TokenGenerator().getToken();

        UUIDToken.put(token, uuid);

        //TODO Database

        return token;
    }

    /**
     * registers the MC user
     *
     * @param user the Discord user
     * @param token the entered token
     */
    public static void linkUser(User user, String token) {

        UUID uuid = UUIDToken.get(token);

        if (token.length() == 5) {
            linkedAccounts.put(user.getId(), uuid);

            //TODO Database

        }

    }

    public static User getDiscordUser(UUID uuid) {

        Iterator it = linkedAccounts.entrySet().iterator();

        while(it.hasNext()) {
            long l = (Long) it.next();

            return DiscordBot.getApi().getServerById(Constants.FK_SERVER).get().getMemberById(l).get();
        }

        return null;
    }

    public static String getPlayerName(long userId) {

        UUID uuid = linkedAccounts.get(userId);

        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);

        return p.getName();
    }

}
