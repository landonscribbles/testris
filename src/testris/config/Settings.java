package testris.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Settings {

    public static HashMap<String, Integer> settings = new HashMap<String, Integer>();

    public HashMap loadSettings() {
        Properties props = new Properties();
        String settingsFileName = "settings.properties";
        try {
            InputStream inputStream = new FileInputStream(settingsFileName);
            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("Couldn't find settings file");
            }
        } catch (IOException ex) {
            System.out.println("Failed to load settings.");
            System.exit(1);
        }

        settings.put("display_width", Integer.parseInt(props.getProperty("width")));
        settings.put("display_height", Integer.parseInt(props.getProperty("height")));

        return settings;
    }
}
