package net.artin13.noelytra;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static net.artin13.noelytra.ElytraHandler.*;

public class EventChecker implements Listener {
    // Check if player exited the end
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        if (event.getFrom().getName().equals("world_the_end")) {
            CloseElytra(event.getPlayer());
        } else if (event.getPlayer().getWorld().getName().equals("world_the_end")) {
            OpenElytra(event.getPlayer());
        }
    }

    // Check if player moves item
    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        if (!event.getWhoClicked().getWorld().getName().equals("world_the_end")) {
            CloseElytra((Player) event.getWhoClicked());
        } else {
            OpenElytra((Player) event.getWhoClicked());
        }
    }

    // Check if player interacts with item
    @EventHandler
    public void onInteraction(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals("world_the_end")) {
            CloseElytra(event.getPlayer());
        } else {
            OpenElytra(event.getPlayer());
        }
    }
}
