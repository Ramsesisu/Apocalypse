package org.apocalypse.core.map;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apocalypse.api.map.Map;
import org.apocalypse.api.service.Record;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Getter
public class MapRecord extends Record<Map> {

    public final File worldFolder = new File(Bukkit.getWorldContainer(), "worlds");

    @SneakyThrows
    public void loadMaps() {
        for (Map map : this.list.values()) {
            File zipFile = new File(Bukkit.getWorldContainer(), "maps/" + map.getClass().getSimpleName().toLowerCase()  + ".zip");
            if (!zipFile.exists()) {
                Bukkit.getServer().getLogger().info("The zip file for map " + map.getName() + " does not exist.");
            }

            try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile.toPath()))) {
                ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    File entryDestination = new File(worldFolder, entry.getName());
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
            Bukkit.getServer().getLogger().info("Unzipped " + map.getName() + " successfully.");
        }
    }
}
