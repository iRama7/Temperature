package rama.spigot.manager;

import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Biomes {

    private List<Biome> biomes;
    private FileConfiguration config;

    public Biomes(FileConfiguration config){
        biomes = new ArrayList<>();
        this.config = config;
    }

    public int getBiomeTemperature(Biome biome){
        String temperatureString = config.getString("biomes." + biome.name() + ".temperature");
        if(temperatureString == null){
            return 0;
        }
        char sign = temperatureString.charAt(0);
        if(sign != '+' && sign != '-'){
            return 0;
        }
        int number = Integer.parseInt(temperatureString.substring(1));
        if(sign == '+'){
            return number;
        }
        return Integer.parseInt(String.valueOf(sign) + number);

    }

}
