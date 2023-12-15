package rama.spigot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import rama.spigot.display.actionbar;
import rama.spigot.display.bossbar;

import java.util.ArrayList;
import java.util.List;

public class TemperedMain implements Listener {

    private List<TemperedPlayer> temperedPlayerList;
    private Temperature main;
    private actionbar actionbar;
    private bossbar bossbar;

    public TemperedMain(Temperature main){
        this.main = main;
        temperedPlayerList = new ArrayList<>();
        actionbar = new actionbar(main);
        this.bossbar = new bossbar(main);
    }



    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        TemperedPlayer TPlayer = new TemperedPlayer(event.getPlayer(), main);
        temperedPlayerList.add(TPlayer);
        TPlayer.calculateTemperature();
        TPlayer.temperatureQueue();
        actionbar.run(TPlayer);
        bossbar.run(TPlayer);
    }

    public TemperedPlayer getTPlayer(Player player){
        TemperedPlayer search = null;
        for(TemperedPlayer TPlayer : temperedPlayerList){
            if(TPlayer.getBukkitPlayer() == player){
                search = TPlayer;
                break;
            }
        }
        return search;
    }

    public List<TemperedPlayer> getTemperedPlayerList(){
        return temperedPlayerList;
    }

}
