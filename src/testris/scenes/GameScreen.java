package testris.scenes;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import testris.playfield.Board;

public class GameScreen implements Scene {

    Boolean sceneRunning = false;
    Scene previousScene;
    Board playBoard;

    public GameScreen(Scene prevScene) {
        previousScene = prevScene;
    }

//    Need to have something generate blocks
//    Need to draw a frame around the play field

    public void start() {
        previousScene.end();
        previousScene = null;
        playBoard = new Board(Display.getWidth(), Display.getHeight());
        sceneRunning = true;
        gameLoop();

    }

    public void gameLoop() {
        System.out.println("Entering game loop");
        while (sceneRunning) {
            if (Display.isCloseRequested()) {
                System.exit(0);
            }
            processInput();
            drawScene();
            Display.update();
        }
    }

    public void drawScene() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        playBoard.draw();
    }

    public void end() {

    }

    public void processInput() {

    }
}
