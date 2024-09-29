package org.apocalypse;

import com.google.common.reflect.ClassPath;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.service.container.Container;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public final class Apocalypse extends JavaPlugin {

    private static Apocalypse INSTANCE;

    public static Apocalypse getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        INSTANCE = this;

        Container.register();

        for (Class<? extends Listener> listener : findClasses(Listener.class)) {
            this.getServer().getPluginManager().registerEvents(listener.getConstructor().newInstance(), getInstance());
        }
    }

    @Override
    public void onDisable() {

    }

    public static Set<Class<?>> findClasses(final String packageName) throws IOException {
        return ClassPath.from(Apocalypse.class.getClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName().startsWith(packageName))
                .map(ClassPath.ClassInfo::load)
                .collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<Class<? extends T>> findClasses(final Class<T> superClass) throws IOException {
        return ClassPath.from(Apocalypse.class.getClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName().startsWith("org.apocalypse.core"))
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> superClass.isAssignableFrom(clazz) && !clazz.equals(superClass))
                .map(clazz -> (Class<? extends T>) clazz)
                .collect(Collectors.toSet());
    }
}
