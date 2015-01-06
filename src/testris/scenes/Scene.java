package testris.scenes;

public interface Scene {

    public void start();

    public void gameLoop();

    public void drawScene();

    public void end();

    public void processInput();
}
