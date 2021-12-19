package io.shaded.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Test extends JavaPlugin {

  @Override
  public void onEnable() {
    new TestBootstrap(this).start();
  }
}
