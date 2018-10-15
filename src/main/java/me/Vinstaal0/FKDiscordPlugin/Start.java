package me.Vinstaal0.FKDiscordPlugin;

import org.javacord.api.DiscordApi;

/**
 * Created by Vinstaal0 on 28-3-2018.
 */
public class Start {

    private static DiscordApi api;

    public static void main(String[] args) {

        new DiscordBot().login(Constants.TOKEN);
    }

}
