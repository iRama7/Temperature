package rama.spigot.manager;

import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import rama.spigot.Temperature;
import rama.spigot.TemperedPlayer;

public class TManager implements Listener {

    private Temperature main;

    public TManager(Temperature main){
        this.main = main;
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event){
        TemperedPlayer TPlayer = main.getTemperedMain().getTPlayer(event.getPlayer());
        Biome playerBiome = event.getPlayer().getLocation().getBlock().getBiome();
        if(TPlayer.getBiome() != playerBiome){
            TPlayer.setBiome(playerBiome);
            TPlayer.calculateTemperature();
        }
    }

}
