package testris.scenes;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;
import testris.blocks.BlockGenerator;
import testris.blocks.PlayerBlock;
import testris.input.InputHandler;
import testris.playfield.Board;

import java.util.ArrayList;
import java.util.HashMap;

public class GameScreen implements Scene {

    InputHandler inputHandler = InputHandler.getInstance();
    Boolean sceneRunning = false;
    Scene previousScene;
    Board playBoard;
    BlockGenerator blockGenerator;
    int maxFrameRate = 60;
    int currentFrame = 0;
    PlayerBlock playerBlock;

    public GameScreen(Scene prevScene) {
        previousScene = prevScene;
    }

    private long getTime() {
        return System.nanoTime() / 1000000;
    }

    public void start() {
        previousScene.end();
        previousScene = null;
        HashMap<String, Float> borderColor = new HashMap<>();
        borderColor.put("red", 0.3f);
        borderColor.put("green", 0.3f);
        borderColor.put("blue", 0.3f);
        playBoard = new Board(Display.getWidth(), Display.getHeight(), borderColor);
        sceneRunning = true;
//        Player block testing
        blockGenerator =  new BlockGenerator(playBoard);
        playerBlock = blockGenerator.generateBlock();
        gameLoop();

    }

    public void gameLoop() {
        System.out.println("Entering game loop");
        while (sceneRunning) {
            if (Display.isCloseRequested()) {
                System.exit(0);
            }
            if (playerBlock.isDead()) {
                playerBlock = blockGenerator.generateBlock();
            }
            processInput();
            playerBlock.update(currentFrame);
            playBoard.update();
            if (playerBlock.isGameOver()) {
                break;
            }
            drawScene();
            Display.update();
            Display.sync(maxFrameRate);
//            Check frame
            currentFrame++;
//            System.out.println("Current frame: " + currentFrame);
            if (currentFrame >= 60) {
                currentFrame = 0;
            }
        }
        System.out.println("Game Over!");
        System.exit(0);
    }

    public void drawScene() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        playerBlock.draw();
        playBoard.draw();
    }

    public void end() {

    }

    public void processInput() {
        HashMap<String, Boolean> pressedInput;
        pressedInput = inputHandler.getPressedInput();
        if (pressedInput.get("left") != null) {
            playerBlock.moveLeft();
        }
        if (pressedInput.get("right") != null) {
            playerBlock.moveRight();
        }

    }
}
