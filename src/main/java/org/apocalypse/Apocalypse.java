package org.apocalypse;

import com.google.common.reflect.ClassPath;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public final class Apocalypse extends JavaPlugin {

    @Override
    public void onEnable() {

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

    public static Set<Class<?>> findClasses(final Class<?> superClass) throws IOException {
        return ClassPath.from(Apocalypse.class.getClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName().startsWith("org.apocalypse.core"))
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> superClass.isAssignableFrom(clazz) && !clazz.equals(superClass))
                .map(clazz -> (Class<?>) clazz)
                .collect(Collectors.toSet());
    }
}
