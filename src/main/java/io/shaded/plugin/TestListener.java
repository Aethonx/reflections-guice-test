package io.shaded.plugin;

import com.google.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TestListener implements Listener {

  private final JavaPlugin plugin;

  @Inject
  public TestListener(JavaPlugin plugin) {
    this.plugin = plugin;
    this.plugin.getLogger().info("Loaded");
  }

  @EventHandler
  void onPlayerJoinEvent(final PlayerJoinEvent event){
    this.plugin.getLogger().info("Player joined");
  }
}
