package io.shaded.plugin;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Locale;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TestBootstrap {

  private static Logger LOGGER = LoggerFactory.getLogger(TestBootstrap.class);

  private final JavaPlugin plugin;
  private Injector injector;

  public TestBootstrap(JavaPlugin plugin) {
    this.plugin = plugin;
    this.injector = Guice.createInjector(new PluginModule(this.plugin));
  }

  public void start() {
    LOGGER.info(
        "Scanning classes with related package " + this.plugin.getClass()
            .getPackageName());

    Reflections reflections = new Reflections(
        this.plugin.getClass().getPackageName());

    reflections
        .getSubTypesOf(Listener.class)
        .stream()
        .map(aClass -> this.injector.getInstance(aClass))
        .forEach(listener -> this.plugin.getServer().getPluginManager()
            .registerEvents(listener, plugin));

    final var map = this.plugin.getServer().getCommandMap();
    reflections
        .getSubTypesOf(Command.class)
        .stream()
        .map(aClass -> this.injector.getInstance(aClass))
        .filter(command -> map.getCommand(command.getName()) == null)
        .forEach(
            command -> map.register(
                this.plugin.getName().toLowerCase(Locale.ROOT),
                command
            )
        );
  }

  private static class PluginModule extends AbstractModule {

    private final JavaPlugin plugin;

    private PluginModule(JavaPlugin plugin) {
      this.plugin = plugin;
    }

    @Override
    protected void configure() {
      this.bind(JavaPlugin.class).toInstance(this.plugin);
    }
  }


}
