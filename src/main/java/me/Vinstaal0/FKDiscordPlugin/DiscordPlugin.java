package me.Vinstaal0.FKDiscordPlugin;

import me.Vinstaal0.FKDiscordPlugin.AccountLinking.SpigotCommands.SpigotDiscordCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Vinstaal0 on 28-3-2018.
 */
public class DiscordPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        loadExternalJars();

        new DiscordBot().login(Constants.TOKEN);

        // Bukkit Commands

        this.getCommand("discord").setExecutor(new SpigotDiscordCommand());

    }

    @Override
    public void onDisable() {

        DiscordBot.getApi().disconnect();

    }

    private void loadExternalJars()
    {
        try
        {
            // TODO Change name of world
            File externalFolder = new File(Bukkit.getServer().getWorld("DungeonRealms").getWorldFolder().getParent(), "external");
            File[] files = externalFolder.listFiles();
            for (File file : files)
                loadFile(file);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void loadFile(File file) throws IOException
    {
        invokeLoader(file.toURI().toURL());
    }

    public static void invokeLoader(URL url) throws IOException
    {
        final URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        final Class<?> sysclass = URLClassLoader.class;

        try
        {
            final Method method = sysclass.getDeclaredMethod("addURL", URL.class);

            method.setAccessible(true);
            method.invoke(sysloader, url);
        } catch (Throwable t)
        {
            t.printStackTrace();

            throw new IOException("Could not add URL to system class loader!");
        }
    }

}
