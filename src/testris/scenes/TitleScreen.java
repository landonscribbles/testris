package testris.scenes;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import testris.input.InputHandler;

import java.awt.Font;
import java.util.HashMap;

// Have each screen require a start method (write an interface) also each one should be passed
// in the previous scene and call it's end method (is this redundant?)
public class TitleScreen implements Scene {

    InputHandler inputHandler = InputHandler.getInstance();
    Boolean sceneRunning = false;
    TrueTypeFont font;

    public TitleScreen() {
    }

//    This should probably be moved to utils
    private void createFont(){
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
    }

    public void start() {
        System.out.println("Entering title screen");
        createFont();
        sceneRunning = true;
        gameLoop();
    }

    public void gameLoop() {
        System.out.println("Entering poll loop");
        while (sceneRunning) {
            if (Display.isCloseRequested()) {
                System.exit(0);
            }
            processInput();
            drawScene();
            Display.update();
        }
    }

    public void processInput() {
        HashMap<String, Boolean> pressedInput;
        pressedInput = inputHandler.getPressedInput();
        if (pressedInput.get("up") != null){
            System.out.println("You pressed up on the title screen!");
        }
    }

    public void drawScene(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        font.drawString(100, 50, "Title Screen!", Color.yellow);
    }

    public void end() {
//        Any cleanup needed can be done here
    }
}
