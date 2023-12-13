package rama.spigot.display;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import rama.spigot.Temperature;
import rama.spigot.TemperedPlayer;

public class actionbar {

    private final Temperature main;

    public actionbar(Temperature main){
        this.main = main;
    }

    private String format(TemperedPlayer TPlayer){
        String formatted = main.getConfig().getString("config.display.actionbar.format");
        if(formatted != null){
            formatted = PlaceholderAPI.setPlaceholders(TPlayer.getBukkitPlayer(), formatted);
        }else{
            formatted = "n/a";
        }
        return formatted;
    }

    private void updateTemperature(TemperedPlayer TPlayer){
        TPlayer.getBukkitPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(main.colorized(format(TPlayer))));
    }

    public void run(TemperedPlayer TPlayer){
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleAsyncRepeatingTask(main, () -> {
            updateTemperature(TPlayer);
        }, 5, 5);
    }


}
