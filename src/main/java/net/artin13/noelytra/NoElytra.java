package net.artin13.noelytra;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

import static net.artin13.noelytra.ElytraHandler.*;

public class NoElytra extends JavaPlugin {
    /*public class ElytraCloseCommand implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by a player!");
                return true;
            }

            Player player = (Player) sender;
            CloseElytra(player);
            return true;
        }
    }
    public class ElytraOpenCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by a player!");
                return true;
            }

            Player player = (Player) sender;
            OpenElytra(player);;
            return true;
        }
    }
    */

    @Override
    public void onEnable() {
        getLogger().info("Balanced elytra succesfully loaded!");
        //this.getCommand("CloseElytra").setExecutor(new ElytraCloseCommand());
        //this.getCommand("OpenElytra").setExecutor(new ElytraOpenCommand());
        getServer().getPluginManager().registerEvents(new EventChecker(), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("Unloaded balanced elytra!");
    }

}
