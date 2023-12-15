package rama.spigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rama.spigot.Temperature;
import rama.spigot.TemperedPlayer;

import java.util.ArrayList;
import java.util.List;

public class Commands implements TabExecutor {

    private Temperature main;

    public Commands(Temperature main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        switch (args.length){
            case 3:
                if(args[0].equalsIgnoreCase("set")){
                    Player player = Bukkit.getPlayer(args[1]);
                    TemperedPlayer TPlayer = main.getTemperedMain().getTPlayer(player);
                    if(player == null || TPlayer == null){
                        commandSender.sendMessage(main.colorized("&c&lERROR &fPlayer &7" + args[1] + " &fis null."));
                    }else{
                        TPlayer.setTemperature(Integer.parseInt(args[2]));
                        commandSender.sendMessage(main.colorized("&a&lSUCCESS &7" + args[1] + " &ftemperature set to " + TPlayer.getTemperatureFormatted() + "&f."));
                    }
                }else if(args[0].equalsIgnoreCase("add")){
                    Player player = Bukkit.getPlayer(args[1]);
                    TemperedPlayer TPlayer = main.getTemperedMain().getTPlayer(player);
                    if(player == null || TPlayer == null){
                        commandSender.sendMessage(main.colorized("&c&lERROR &fPlayer &7" + args[1] + " &fis null."));
                    }else{
                        TPlayer.addTemperature(Integer.parseInt(args[2]));
                        commandSender.sendMessage(main.colorized("&a&lSUCCESS &7" + args[1] + " &ftemperature set to " + TPlayer.getTemperatureFormatted() + "&f."));
                    }
                } else if(args[0].equalsIgnoreCase("remove")) {
                    Player player = Bukkit.getPlayer(args[1]);
                    TemperedPlayer TPlayer = main.getTemperedMain().getTPlayer(player);
                    if(player == null || TPlayer == null){
                        commandSender.sendMessage(main.colorized("&c&lERROR &fPlayer &7" + args[1] + " &fis null."));
                    }else{
                        TPlayer.addTemperature(-Integer.parseInt(args[2]));
                        commandSender.sendMessage(main.colorized("&a&lSUCCESS &7" + args[1] + " &ftemperature set to " + TPlayer.getTemperatureFormatted() + "&f."));
                    }
                }
        }

        return false;
    }



    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(sender.hasPermission("temperature.admin")){
            switch(args.length){
                case 1:
                    commands.add("set");
                    commands.add("add");
                    commands.add("remove");
                    StringUtil.copyPartialMatches(args[0], commands, completions);
                    return completions;
                case 2:
                    for(TemperedPlayer TPlayer : main.getTemperedMain().getTemperedPlayerList()){
                        commands.add(TPlayer.getBukkitPlayer().getName());
                    }
                    StringUtil.copyPartialMatches(args[1], commands, completions);
                    return completions;
                case 3:
                    commands.add("0");
                    StringUtil.copyPartialMatches(args[2], commands, completions);
                    return completions;
                default:
                    break;
            }
        }


        return null;
    }
}
