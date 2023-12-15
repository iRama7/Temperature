package rama.spigot.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rama.spigot.Temperature;

public class Placeholders extends PlaceholderExpansion {

    private Temperature main;

    public Placeholders(Temperature main){
        this.main = main;
    }


    @Override
    public @NotNull String getIdentifier() {
        return "temperature";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Temperature - Spigot";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {

        if(params.equals("formatted")){
            if(player != null && main.getTemperedMain().getTPlayer(player) != null){
                return String.valueOf(main.getTemperedMain().getTPlayer(player).getTemperatureFormatted());
            }
        }

        return params;
    }
}
