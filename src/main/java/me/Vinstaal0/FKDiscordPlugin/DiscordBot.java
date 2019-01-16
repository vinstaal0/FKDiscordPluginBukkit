package me.Vinstaal0.FKDiscordPlugin;

import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import me.Vinstaal0.FKDiscordPlugin.AccountLinking.DiscordCommands.DiscordCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.util.logging.ExceptionLogger;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;
import org.slf4j.Logger;

import java.util.logging.LogManager;

/**
 * Created by Vinstaal0 on 28-3-2018.
 */
public class DiscordBot {

    private static final Logger logger  = null;

    private static DiscordApi api;

    public void login(String token) {

        // Enable debugging, if no slf4j logger was found
        FallbackLoggerConfiguration.setDebug(true);
        FallbackLoggerConfiguration.setTrace(true);

        this.api = new DiscordApiBuilder()
                .setToken(token)
                .setWaitForServersOnStartup(false)
                .login()
                .join();

        System.out.println("Servers: " + api.getServers().toString());

        this.log("Test");

        this.api.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("!ping")) {
                System.out.println("Pong");
                event.getChannel().sendMessage("Pong!");
                event.getChannel().sendMessage("Servers: " + api.getServers());
            }

        });

        // Log a message, if the bot joined or left a server
        api.addServerJoinListener(event -> System.out.println("Joined server " + event.getServer().getName()));
        api.addServerLeaveListener(event -> System.out.println("Left server " + event.getServer().getName()));

        System.out.println("Servers: " + api.getServers().toString());

        // Discord commands
        CommandHandler cmdHandler = new JavacordHandler(api);

        cmdHandler.registerCommand(new DiscordCommand());

        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());

    }

    /**
     * Succesfully connected to Discord
     *
     * @param api The discord api.
     */
    public void onSuccess(DiscordApi api) {

        logger.info("Amount of servers: {}", api.getServers().size());
        logger.info("Connected to Discord account {}", api.getYourself());
        logger.info("Version loaded: " + Constants.VERSION);

        CommandHandler cmdHandler = new CommandHandler() {
            @Override
            public void registerCommand(CommandExecutor executor) {
                super.registerCommand(executor);
            }

        };

        cmdHandler.registerCommand(new DiscordCommand());
    }

    public static DiscordApi getApi() {
        return api;

    }

    public static void log(String message) {

        for (Server server : api.getServers()) {

            if (server.getId() == Constants.FK_SERVER) {
                ServerChannel serverChannel = server.getChannelById(Constants.CONSOLE_CHANNEL).get();

                if (serverChannel instanceof ServerTextChannel) {
                    ServerTextChannel serverTextChannel = ((ServerTextChannel) serverChannel);

                    serverTextChannel.sendMessage(message);
                }
            }
        }
    }

}
