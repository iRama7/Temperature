package rama.spigot.display;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitScheduler;
import rama.spigot.Temperature;
import rama.spigot.TemperedPlayer;

public class bossbar {

    private Temperature main;
    private Boolean enabled;
    private BossBar bar;

    public bossbar(Temperature main){
        this.main = main;
        enabled = main.getConfig().getBoolean("config.display.bossbar.enable");
        bar = Bukkit.createBossBar("n/a", BarColor.valueOf(main.getConfig().getString("config.display.bossbar.color")), BarStyle.valueOf(main.getConfig().getString("config.display.bossbar.style")));
        bar.setVisible(true);
    }

    private String format(TemperedPlayer TPlayer){
        String formatted = main.getConfig().getString("config.display.bossbar.format");
        if(formatted != null){
            formatted = PlaceholderAPI.setPlaceholders(TPlayer.getBukkitPlayer(), formatted);
        }else{
            formatted = "n/a";
        }
        return formatted;
    }

    private void updateTemperature(TemperedPlayer TPlayer){
        bar.setTitle(main.colorized(format(TPlayer)));
        if(!bar.getPlayers().contains(TPlayer.getBukkitPlayer())){
            bar.addPlayer(TPlayer.getBukkitPlayer());
        }
    }

    public void run(TemperedPlayer TPlayer){
        if(enabled) {
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.scheduleAsyncRepeatingTask(main, () -> updateTemperature(TPlayer), 5, 5);
        }
    }

}
