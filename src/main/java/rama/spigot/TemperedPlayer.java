package rama.spigot;


import org.bukkit.entity.Player;

public class TemperedPlayer {

    private final Player bukkitPlayer;
    private int temperature;

    public TemperedPlayer(Player player){
        bukkitPlayer = player;
        temperature = 20;
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    public int getTemperature(){
        return temperature;
    }

}
