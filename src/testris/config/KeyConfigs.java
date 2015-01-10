package testris.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class KeyConfigs {

    public static HashMap<Integer, String> keyConfigs = new HashMap<>();
    private ArrayList<String> inputNames = new ArrayList<String>();

    public KeyConfigs() {
        inputNames.add("up");
        inputNames.add("down");
        inputNames.add("left");
        inputNames.add("right");
//        inputNames.add("rotate_right");
//        inputNames.add("rotate_left");
        inputNames.add("confirm");
        inputNames.add("escape");
    }

    public HashMap loadKeyConfigs() {

        Properties props = new Properties();
        String propFileName = "keybindings.properties";

        try {
            InputStream inputStream = new FileInputStream(propFileName);
            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("Couldn't find property file: " + propFileName);
            }
        } catch (IOException ex) {
            System.out.println("Failed to load key configs");
            System.exit(1);
        }

        keyConfigs.put(Integer.parseInt(props.getProperty("up_key")), "up");
        keyConfigs.put(Integer.parseInt(props.getProperty("down_key")), "down");
        keyConfigs.put(Integer.parseInt(props.getProperty("left_key")), "left");
        keyConfigs.put(Integer.parseInt(props.getProperty("right_key")), "right");
//        keyConfigs.put(Integer.parseInt(props.getProperty("rotate_left_key")), "rotate_left");
//        keyConfigs.put(Integer.parseInt(props.getProperty("rotate_right_key")), "rotate_right");
        keyConfigs.put(Integer.parseInt(props.getProperty("select_key")), "confirm");
        keyConfigs.put(Integer.parseInt(props.getProperty("escape_key")), "escape");

        return keyConfigs;
    }

    public ArrayList getInputNames() {
        return inputNames;
    }
}
