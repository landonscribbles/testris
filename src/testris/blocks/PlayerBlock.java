package testris.blocks;

import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerBlock implements Block {
    private ArrayList<ArrayList> blocks;
    private ArrayList<Point> gridLocations;
    private HashMap<String, Float> blockColor;
    private boolean blockIsControlled;

    public PlayerBlock(ArrayList<ArrayList> blockArray, HashMap<String, Float> color, ArrayList<Point> gridLoc) {
        blocks = blockArray;
        gridLocations = gridLoc;
        blockColor = color;
        blockIsControlled = true;
    }
//    Player blocks should be passed in the board or board state of where the top collidable
//    edge is to then check each of it's sections to see if it is going to collide on each
//    downward movement if it does it should be added to the board and then be marked
//    as no longer controllable
    public void draw(){

    }

    public void gridLocation(ArrayList<Point> gridLocs) {
        gridLocations = gridLocs;
    }

    public HashMap<String, Float> getColor() {
        return blockColor;
    }

    public int[] getGridLocation() { return gridLocation; }

    public void setGridLocation(int[] gridLocation) { gridLocation = gridLocation; }

    public boolean isDead() { return blockIsDead; }

    // Check collision and then set dead if so

    // Take input from the player and do something, should this be on the playfield?
}
