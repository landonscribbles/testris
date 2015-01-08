package testris.playfield;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    int displayWidth;
    int displayHeight;
    int boardWidth;
    int boardHeight;
    int borderWidth = 15;
    ArrayList<Point> topBorderQuad = new ArrayList<>();
    ArrayList<Point> rightBorderQuad = new ArrayList<>();
    ArrayList<Point> bottomBorderQuad = new ArrayList<>();
    ArrayList<Point> leftBorderQuad = new ArrayList<>();
//    Test garbage
    HashMap<String, Float> borderColor;
    

    public Board (int dWidth, int dHeight) {
        displayWidth = dWidth;
        displayHeight = dHeight;
        boardWidth = displayWidth / 3;
        boardHeight = displayHeight;
        borderColor = new HashMap<>();
//        Clean me up
        borderColor.put("red", 0.5f);
        borderColor.put("green", 1.0f);
        borderColor.put("blue", 0.5f);
    }

    private void createBorder() {
//        create 4 different quads and have them connect at corners
//        top quad
        Point topBorderTopLeft = new Point(boardWidth, 0); // Works because split into thirds
        topBorderQuad.add(topBorderTopLeft);
        Point topBorderTopRight = new Point(boardWidth * 2, 0); // Again works because of thirds
        topBorderQuad.add(topBorderTopRight);
        Point topBorderBottomLeft = new Point(boardWidth, borderWidth);
        topBorderQuad.add(topBorderBottomLeft);
        Point topBorderBottomRight = new Point(boardWidth * 2, borderWidth);
        topBorderQuad.add(topBorderBottomRight);
    }

    public void update() {
//        Should be called on each cycle to see if lines need to be cleared and then update
//        blocks
//        Also need to check for a game over here too
    }

    public void addBlock() {
//        register blocks to the board once they have hit the bottom of the board
//        where the bottom is either the actual bottom of the board or the highest added block
    }

    public void draw() {
//        Draw borders
//        Clean me up
        GL11.glColor3f(borderColor.get("red"), borderColor.get("green"), borderColor.get("blue"));
        GL11.glBegin(GL11.GL_QUADS);
//        for (Point point:topBorderQuad) {
//            GL11.glVertex2f(point.getX(), point.getY());
//        }
        GL11.glVertex2f(100,100);
        GL11.glVertex2f(200,100);
        GL11.glVertex2f(200,200);
        GL11.glVertex2f(100,200);
        GL11.glEnd();
    }
}
