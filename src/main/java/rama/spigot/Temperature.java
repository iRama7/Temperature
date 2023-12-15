package rama.spigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import rama.spigot.commands.Commands;
import rama.spigot.display.actionbar;
import rama.spigot.display.bossbar;
import rama.spigot.manager.Biomes;
import rama.spigot.manager.TManager;
import rama.spigot.papi.Placeholders;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Temperature extends JavaPlugin {

    private TemperedMain temperedMain;

    private Biomes biomes;

    private File biomesFile;
    private FileConfiguration biomesConfiguration;
    private TManager tManager;

    @Override
    public void onEnable() {
        registerPaPi();
        registerConfig();
        loadBiomesConfig();
        biomes = new Biomes(biomesConfiguration);
        temperedMain = new TemperedMain(this);
        tManager = new TManager(this);
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public void registerConfig(){
        this.saveDefaultConfig();
    }

    public void registerEvents(){
        Bukkit.getServer().getPluginManager().registerEvents(temperedMain, this);
        Bukkit.getServer().getPluginManager().registerEvents(tManager, this);

    }

    public void registerCommands(){
        TabExecutor commands = new Commands(this);
        this.getCommand("temperature").setTabCompleter(commands);
        this.getCommand("temperature").setExecutor(commands);
    }


    public String colorized(String message){
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            for (char c : ch) {
                builder.append("&" + c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }


    public TemperedMain getTemperedMain() {
        return temperedMain;
    }

    private void registerPaPi(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholders(this).register();
        }else{
            //todo
        }
    }

    public void log(String message){
        this.getServer().getConsoleSender().sendMessage(colorized(message));
    }

    private void loadBiomesConfig() {
        biomesFile = new File(getDataFolder(), "biomes.yml");
        if (!biomesFile.exists()) {
            biomesFile.getParentFile().mkdirs();
            saveResource("biomes.yml", false);
        }

        biomesConfiguration = new YamlConfiguration();
        try {
            biomesConfiguration.load(biomesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Biomes getBiomes(){
        return biomes;
    }
}
