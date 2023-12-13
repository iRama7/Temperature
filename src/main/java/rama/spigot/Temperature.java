package rama.spigot;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import rama.spigot.display.actionbar;
import rama.spigot.papi.Placeholders;

public final class Temperature extends JavaPlugin {

    private actionbar actionbar;
    private TemperedMain temperedMain;

    @Override
    public void onEnable() {
        registerPaPi();
        registerConfig();
        actionbar = new actionbar(this);
        temperedMain = new TemperedMain(this);
        registerEvents();
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
    }


    public String colorized(String s){
        return s;
    }

    public rama.spigot.display.actionbar getActionbar() {
        return actionbar;
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
}
