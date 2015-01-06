package testris;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import testris.config.KeyConfigs;
import testris.config.Settings;
import testris.input.InputHandler;
import testris.scenes.TitleScreen;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        Load game settings
        HashMap<String, Integer> settings = new Settings().loadSettings();

//        Start up the lwjgl machinery
        try{
            Display.setDisplayMode(
                new DisplayMode(
                    settings.get("display_width"), settings.get("display_height")
                )
            );
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }
//        Init OpenGL
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(
            0, settings.get("display_width"), settings.get("display_height"), 0, 1, -1
        );
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        Load the key configs in the properties file
        KeyConfigs keyConfigs = new KeyConfigs();
        HashMap<String, String> keyMapping = keyConfigs.loadKeyConfigs();
//        Instantiate the input handler and pass in the key bindings
        InputHandler inputHandler = InputHandler.getInstance();
        inputHandler.setKeyBindings(keyMapping);
        inputHandler.setInputNames(keyConfigs.getInputNames());
        TitleScreen title = new TitleScreen();
        title.start();
        Display.destroy();

    }
}
