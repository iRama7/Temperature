package rama.spigot;


import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class TemperedPlayer {

    private final Player bukkitPlayer;
    private int temperature;
    private final Temperature main;
    private Biome biome;
    private final int normalTemp;
    private int objTemperatuere;


    public TemperedPlayer(Player player, Temperature main){
        this.main = main;
        bukkitPlayer = player;
        biome = player.getLocation().getBlock().getBiome();
        normalTemp = main.getConfig().getInt("config.temperature.normal_temperature");
        temperature = normalTemp;
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    public int getTemperature(){
        return temperature;
    }

    public String getTemperatureFormatted(){
        String formatted = main.getConfig().getString("config.temperature.format");
        if(formatted == null || !formatted.contains("x")){
            main.log("&c&lERROR &f-> &7'&aconfig.temperature.format&7' &cis null or malformed.");
            return main.colorized("&c&lCONFIG ERROR");
        }
        formatted = formatted.replaceFirst("x", String.valueOf(temperature));
        return formatted;
    }

    public void temperatureQueue(){
        long delay = main.getConfig().getLong("config.temperature.change_delay");
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {

                if(temperature < objTemperatuere){
                    temperature++;
                }
                if(temperature > objTemperatuere){
                    temperature--;
                }
            }
        }, delay, delay);
    }

    public void setTemperature(int t){
        temperature = t;
    }

    public void addTemperature(int t){
        temperature+=t;
    }


    public Biome getBiome(){
        return biome;
    }

    public void setBiome(Biome newBiome){
        biome = newBiome;
    }

    public void calculateTemperature(){
        int biomeTemperature = main.getBiomes().getBiomeTemperature(biome);
        objTemperatuere = normalTemp + biomeTemperature;
    }

}
