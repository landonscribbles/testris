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
    int borderXWidth = 8;
    int borderYWidth = 50;
    ArrayList<Point> playFieldPoints = new ArrayList<>();
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
        createBorder();
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
        Point topBorderBottomRight = new Point(boardWidth * 2, borderYWidth);
        topBorderQuad.add(topBorderBottomRight);
        Point topBorderBottomLeft = new Point(boardWidth, borderYWidth);
        topBorderQuad.add(topBorderBottomLeft);
//        Right quad
        Point rightBorderTopLeft = new Point(boardWidth * 2 - borderXWidth, 0);
        rightBorderQuad.add(rightBorderTopLeft);
        Point rightBorderTopRight = new Point(boardWidth * 2, 0);
        rightBorderQuad.add(rightBorderTopRight);
        Point rightBorderBottomRight = new Point(boardWidth * 2, displayHeight);
        rightBorderQuad.add(rightBorderBottomRight);
        Point rightBorderBottomLeft = new Point(boardWidth * 2 - borderXWidth, displayHeight);
        rightBorderQuad.add(rightBorderBottomLeft);
//        Bottom quad
        Point bottomBorderTopLeft = new Point(boardWidth, displayHeight - borderYWidth);
        bottomBorderQuad.add(bottomBorderTopLeft);
        Point bottomBorderTopRight = new Point(boardWidth * 2, displayHeight - borderYWidth);
        bottomBorderQuad.add(bottomBorderTopRight);
        Point bottomBorderBottomRight = new Point(boardWidth * 2, displayHeight);
        bottomBorderQuad.add(bottomBorderBottomRight);
        Point bottomBorderBottomLeft = new Point(boardWidth, displayHeight);
        bottomBorderQuad.add(bottomBorderBottomLeft);
//        Left quad
        Point leftBorderTopRight = new Point(boardWidth + borderXWidth, 0);
        leftBorderQuad.add(leftBorderTopRight);
        Point leftBorderTopLeft = new Point(boardWidth, 0);
        leftBorderQuad.add(leftBorderTopLeft);
        Point leftBorderBottomLeft = new Point(boardWidth, displayHeight);
        leftBorderQuad.add(leftBorderBottomLeft);
        Point leftBorderBottomRight = new Point(boardWidth + borderXWidth, displayHeight);
        leftBorderQuad.add(leftBorderBottomRight);
//        Add the play field points
        Point upperLeftPlayField = new Point(boardWidth + borderXWidth, borderYWidth);
        Point upperRightPlayField = new Point(boardWidth * 2 - borderXWidth, borderYWidth);
        Point bottomRightPlayField = new Point(boardWidth * 2 - borderXWidth, displayHeight - borderYWidth);
        Point bottomLeftPlayFiend = new Point(boardWidth + borderXWidth, displayHeight - borderYWidth);
        playFieldPoints.add(upperLeftPlayField);
        playFieldPoints.add(upperRightPlayField);
        playFieldPoints.add(bottomRightPlayField);
        playFieldPoints.add(bottomLeftPlayFiend);
    }

    public ArrayList<Point> getPlayFieldDimensions(){
//        Add me!
        return playFieldPoints;
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

    private void drawBorder() {
        GL11.glColor3f(borderColor.get("red"), borderColor.get("green"), borderColor.get("blue"));
        GL11.glBegin(GL11.GL_QUADS);
        for (Point point : topBorderQuad) {
            GL11.glVertex2f(point.getX(), point.getY());
        }
        GL11.glEnd();
        GL11.glBegin(GL11.GL_QUADS);
        for (Point point : rightBorderQuad) {
            GL11.glVertex2f(point.getX(), point.getY());
        }
        GL11.glEnd();
        GL11.glBegin(GL11.GL_QUADS);
        for (Point point : bottomBorderQuad) {
            GL11.glVertex2f(point.getX(), point.getY());
        }
        GL11.glEnd();
        GL11.glBegin(GL11.GL_QUADS);
        for (Point point : leftBorderQuad) {
            GL11.glVertex2f(point.getX(), point.getY());
        }
        GL11.glEnd();
    }

    public void draw() {
//        Draw borders
//        Clean me up
        drawBorder();
    }
}
