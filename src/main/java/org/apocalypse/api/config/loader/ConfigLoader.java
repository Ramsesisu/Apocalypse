package org.apocalypse.api.config.loader;

import org.apocalypse.api.map.wrapper.MapsWrapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.InputStream;

public class ConfigLoader {

    public static MapsWrapper loadMapsConfig(String fileName) {
        LoaderOptions loaderOptions = new LoaderOptions();
        Constructor constructor = new Constructor(MapsWrapper.class, loaderOptions);
        DumperOptions dumperOptions = new DumperOptions();
        Representer representer = new Representer(dumperOptions);
        Yaml yaml = new Yaml(constructor, representer, dumperOptions, loaderOptions);

        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + fileName);
            }
            return yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration from " + fileName, e);
        }
    }
}