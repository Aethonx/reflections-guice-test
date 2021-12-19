package io.shaded.plugin;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class CommandExample extends Command {

  @Inject
  CommandExample(JavaPlugin plugin) {
    super("test");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender,
      @NotNull String commandLabel, @NotNull String[] args) {
    sender.sendMessage("test command example");
    return false;
  }
}
