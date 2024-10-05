package org.apocalypse.core.map;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.config.MapConfig;
import org.apocalypse.api.config.loader.ConfigLoader;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.apocalypse.api.map.factory.MapFactory.mapsWrapper;

@Getter
public class MapLoader {

    @SneakyThrows
    public static void loadMaps() {
        mapsWrapper = ConfigLoader.loadMapsConfig("maps.yml");
        for (MapConfig config : mapsWrapper.getMaps()) {

            File zipFile = new File(Bukkit.getWorldContainer(), "maps/" + config.getName().replace(" ", "").toLowerCase() + ".zip");
            if (!zipFile.exists()) {
                Bukkit.getServer().getLogger().info("The zip file for map " + config.getName() + " does not exist.");
                continue;
            }

            try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile.toPath()))) {
                ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    File entryDestination = new File(new File(Bukkit.getWorldContainer(), "worlds"), entry.getName());
                    if (entry.isDirectory()) {
                        entryDestination.mkdirs();
                    } else {
                        entryDestination.getParentFile().mkdirs();
                        try (FileOutputStream out = new FileOutputStream(entryDestination)) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = zipInputStream.read(buffer)) > 0) {
                                out.write(buffer, 0, len);
                            }
                        }
                    }
                }
            }
            Bukkit.getServer().getLogger().info("Unzipped " + config.getName() + " successfully.");
        }
    }
}