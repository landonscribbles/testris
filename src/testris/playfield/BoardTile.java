package testris.playfield;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import java.util.HashMap;

public class BoardTile {
    Point gridLocation;
    Point pixelLocation;
    int size;
    int borderSize;
    HashMap<String, Float> color;
    Boolean empty;
    Boolean toDraw;
//    TODO created border around tile

    public BoardTile(Point gridLocation, Point pixelLocation, Integer size) {
        this.gridLocation = gridLocation;
        this.pixelLocation = pixelLocation;
        this.size = size;
        empty = true;
        toDraw = false;
        borderSize = 8;
    }

    public Point getGridLocation() {
        return gridLocation;
    }

    public void drawBorder() {
        if (toDraw) {
            for (int i=1; i<(borderSize+1); i++) {
                HashMap<String, Float> bColor = new HashMap<>();
                int distanceFromEdge = borderSize - i;
//                System.out.println("i: " +  i);
//                System.out.println("Dist from edge: " + distanceFromEdge);
                for (String key: color.keySet()) {
                    float nc = color.get(key) - (i / 10.0f);
                    if (nc < 0.0f) {
                        nc = 0.0f;
                    }
                    bColor.put(key, nc);
                }
                GL11.glColor3f(bColor.get("red"), bColor.get("green"), bColor.get("blue"));
//                Top border
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(pixelLocation.getX() - distanceFromEdge, pixelLocation.getY() + distanceFromEdge);
                GL11.glVertex2f(pixelLocation.getX() + size - distanceFromEdge, pixelLocation.getY() + distanceFromEdge);
                GL11.glVertex2f(pixelLocation.getX() + size - distanceFromEdge, pixelLocation.getY() + distanceFromEdge + 1);
                GL11.glVertex2f(pixelLocation.getX() + distanceFromEdge, pixelLocation.getY() + distanceFromEdge + 1);
                GL11.glEnd();
//                Right border
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(pixelLocation.getX() + size - distanceFromEdge, pixelLocation.getY() + distanceFromEdge);
                GL11.glVertex2f(pixelLocation.getX() + size - distanceFromEdge + 1, pixelLocation.getY() - distanceFromEdge);
                GL11.glVertex2f(pixelLocation.getX() + size - distanceFromEdge + 1, pixelLocation.getY() - distanceFromEdge + size + 1); // The weird +1 here fixes a slight gap
                GL11.glVertex2f(pixelLocation.getX() + size - distanceFromEdge, pixelLocation.getY() - distanceFromEdge + size);
                GL11.glEnd();
//                Bottom border
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(pixelLocation.getX() + distanceFromEdge, pixelLocation.getY() + size - distanceFromEdge);
                GL11.glVertex2f(pixelLocation.getX() + size + distanceFromEdge, pixelLocation.getY() + size - distanceFromEdge);
                GL11.glVertex2f(pixelLocation.getX() + size - distanceFromEdge, pixelLocation.getY() + size - distanceFromEdge + 1);
                GL11.glVertex2f(pixelLocation.getX() - distanceFromEdge, pixelLocation.getY() + size - distanceFromEdge + 1);
                GL11.glEnd();
//                Left border
                GL11.glBegin((GL11.GL_QUADS));
                GL11.glVertex2f(pixelLocation.getX() + distanceFromEdge, pixelLocation.getY() + distanceFromEdge);
                GL11.glVertex2f(pixelLocation.getX() + distanceFromEdge + 1, pixelLocation.getY() + distanceFromEdge);
                GL11.glVertex2f(pixelLocation.getX() + distanceFromEdge + 1, pixelLocation.getY() - distanceFromEdge + size);
                GL11.glVertex2f(pixelLocation.getX() + distanceFromEdge, pixelLocation.getY() + distanceFromEdge + size);
                GL11.glEnd();
            }
        } else {
//            Nothing
        }
    }

    public void draw() {
        if (toDraw) {
            GL11.glColor3f(color.get("red"), color.get("green"), color.get("blue"));
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(pixelLocation.getX(), pixelLocation.getY());
            GL11.glVertex2f(pixelLocation.getX() + size, pixelLocation.getY());
            GL11.glVertex2f(pixelLocation.getX() + size, pixelLocation.getY() + size);
            GL11.glVertex2f(pixelLocation.getX(), pixelLocation.getY() + size);
            GL11.glEnd();
        } else {
//            Do nothing
        }
        drawBorder();
    }

    public void setFilled() {
        empty = false;
        toDraw = true;
    }

    public void setToDraw() {
        toDraw = true;
    }

    public Boolean isEmpty() {
        return empty;
    }

    public void setGridLocation(Point gridLocation) {
        this.gridLocation = gridLocation;
    }

    public Point getPixelLocation() {
        return pixelLocation;
    }

    public void setPixelLocation(Point pixelLocation) {
        this.pixelLocation = pixelLocation;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HashMap<String, Float> getColor() {
        return color;
    }

    public void setColor(HashMap<String, Float> color) {
        this.color = color;
    }
}
