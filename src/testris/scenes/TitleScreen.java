package testris.scenes;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import testris.input.InputHandler;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

// Have each screen require a start method (write an interface) also each one should be passed
// in the previous scene and call it's end method (is this redundant?)
public class TitleScreen implements Scene {

    InputHandler inputHandler = InputHandler.getInstance();
    Boolean sceneRunning = false;
    TrueTypeFont optionsFont;
    TrueTypeFont titleFont;
    Cursor cursor;
    int startTextX;
    int startTextY;
    int exitTextX;
    int exitTextY;
    int displayWidth;
    int displayHeight;

    public TitleScreen() {
    }

//    This should probably be moved to utils
    private void createFont(){
        Font awtOptionsFont = new Font("Times New Roman", Font.BOLD, 24);
        optionsFont = new TrueTypeFont(awtOptionsFont, false);
        Font awtTitleFont = new Font("Times New Roman", Font.BOLD, 60);
        titleFont = new TrueTypeFont(awtTitleFont, false);
    }

    private class Cursor {
        int x;
        int y;
        int sideLength = 20;
        HashMap<String, Float> color;
//        What option are we current pointing at?
        boolean atStart = true;

        public Cursor(HashMap<String, Float> startColor) {
            color = startColor;
        }

        public void setColor(HashMap<String, Float> newColor) {
            color = newColor;
        }

        public void draw() {
            GL11.glColor3f(color.get("red"), color.get("green"), color.get("blue"));
            if (atStart) {
                x = startTextX - 20;
                y = startTextY + 5;
            } else {
                x = exitTextX - 20;
                y = exitTextY + 5;
            }
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex2f(x, y);
            GL11.glVertex2f(x, y + sideLength);
            GL11.glVertex2f(x + sideLength / 2, y + sideLength / 2);
            GL11.glEnd();
        }

        public void toggleCursor() {
            atStart = !atStart;
        }

    }

    public void start() {
        System.out.println("Entering title screen");
        createFont();
        HashMap<String, Float> cursorColor = new HashMap<>();
        cursorColor.put("red", 0.5f);
        cursorColor.put("green", 1.0f);
        cursorColor.put("blue", 0.5f);
        cursor = new Cursor(cursorColor);
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
            cursor.toggleCursor();
        }
        if (pressedInput.get("down") != null) {
            cursor.toggleCursor();
        }
    }

    public void drawScene(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        displayWidth = Display.getWidth();
        displayHeight = Display.getHeight();

        GL11.glEnable(GL11.GL_BLEND);
        int titleTextX = displayWidth / 2 - 140;
        int titleTextY = displayHeight / 2 - 100;
        titleFont.drawString(titleTextX, titleTextY, "TESTRIS", Color.darkGray);

        startTextX = displayWidth / 2 - 70;
        startTextY = displayHeight / 2;
        optionsFont.drawString(startTextX, startTextY, "Start Game", Color.blue);

        exitTextX = displayWidth / 2 - 70;
        exitTextY = displayHeight / 2 + 30;
        optionsFont.drawString(exitTextX, exitTextY, "Exit", Color.blue);
        GL11.glDisable(GL11.GL_BLEND);

        cursor.draw();

    }

    public void end() {
//        Any cleanup needed can be done here
    }
}
