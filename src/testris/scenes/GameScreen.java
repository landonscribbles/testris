package testris.scenes;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;
import testris.blocks.BlockGenerator;
import testris.playfield.Board;

import java.util.ArrayList;

public class GameScreen implements Scene {

    ArrayList<Point> boardPoints;
    Boolean sceneRunning = false;
    Scene previousScene;
    Board playBoard;
    BlockGenerator blockGenerator;

    public GameScreen(Scene prevScene) {
        previousScene = prevScene;
    }

//    Need to have something generate blocks

//    Dooooooonne!
//    Need to draw a frame around the play field

    public void start() {
        previousScene.end();
        previousScene = null;
        playBoard = new Board(Display.getWidth(), Display.getHeight());
        sceneRunning = true;
//        Testing junk
        boardPoints = playBoard.getPlayFieldDimensions();
        int boardHeight = boardPoints.get(3).getY() - boardPoints.get(0).getY();
        int yGridSize = boardHeight / 20;
        System.out.println("Board height: " + boardHeight + " Board Y grid size: " + yGridSize);
        int boardWidth = boardPoints.get(1).getX() - boardPoints.get(0).getX();
        int xGridSize = boardWidth / 10;
        System.out.println("Board width: " + boardWidth + " Board X grid size: " + xGridSize);
        blockGenerator = new BlockGenerator(xGridSize, yGridSize);
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
