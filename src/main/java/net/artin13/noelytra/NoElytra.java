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

public class NoElytra extends JavaPlugin {
    public class ElytraCloseCommand implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by a player!");
                return true;
            }

            Player player = (Player) sender;
            Inventory inventory = player.getInventory();

            // Loop through the player's inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null && item.getType() == Material.ELYTRA) {
                    ItemMeta elytrameta = item.getItemMeta();
                    // Create a leather chestplate and transfer properties
                    ItemStack leatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE, item.getAmount());
                    inventory.setItem(i, leatherChestplate);
                    leatherChestplate = inventory.getItem(i);
                    ItemMeta meta = leatherChestplate.getItemMeta();
                    // Transfer enchantments
                    for (Enchantment enchantment : item.getEnchantments().keySet()) {
                        int level = item.getEnchantmentLevel(enchantment);
                        meta.addEnchant(enchantment, level, true);
                    }
                    // Get and transfer durability
                    if (meta instanceof Damageable) {
                        ((Damageable) meta).setDamage(((Damageable) elytrameta).getDamage());
                        meta.setUnbreakable(true);
                    }
                    // Set display name
                    meta.setDisplayName("§bClosed Elytra");
                    meta.setLore(Arrays.asList("Elytra are unusable in this dimension,","Enter The End to reopen",String.format("§fDurability: %s/432",432-((Damageable) elytrameta).getDamage())));
                    leatherChestplate.setItemMeta(meta);
                }
            }

            player.sendMessage("All elytras in your inventory have been replaced with leather chestplates named 'Closed Elytra'.");
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
            Inventory inventory = player.getInventory();

            // Loop through the player's inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().get(0).equals("Elytra are unusable in this dimension,")) {
                    ItemMeta leathermeta = item.getItemMeta();
                    // Create a leather chestplate and transfer properties
                    ItemStack elytra = new ItemStack(Material.ELYTRA, item.getAmount());
                    inventory.setItem(i, elytra);
                    elytra = inventory.getItem(i);
                    ItemMeta meta = elytra.getItemMeta();
                    // Transfer enchantments
                    for (Enchantment enchantment : item.getEnchantments().keySet()) {
                        if (enchantment.equals(Enchantment.MENDING) || enchantment.equals(Enchantment.DURABILITY)) {
                            int level = item.getEnchantmentLevel(enchantment);
                            meta.addEnchant(enchantment, level, true);
                        }
                    }

                    // Get and transfer durability
                    if (meta instanceof Damageable) {
                        ((Damageable) meta).setDamage(((Damageable) leathermeta).getDamage());
                        meta.setUnbreakable(false);
                    }
                    elytra.setItemMeta(meta);
                }
            }

            player.sendMessage("All closed elytras in your inventory have been replaced with opened ones.");
            return true;
        }
    }



    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        this.getCommand("CloseElytra").setExecutor(new ElytraCloseCommand());
        this.getCommand("OpenElytra").setExecutor(new ElytraOpenCommand());
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }

}
