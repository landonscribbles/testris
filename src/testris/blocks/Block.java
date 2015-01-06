package testris.blocks;

public interface Block {
    // Return the points of the quad
    public int[] getDrawPoints();

    //Set the points of the quad
    public void setDrawPoints(int[] drawPoints);

    //Return the color of the block
    public float[] getColor();

    // Get the block's location on the playfield frid
    public int[] getGridLocation();

    // Set the block's location on the playfield grid
    public void setGridLocation(int[] gridLocation);

    // Does this block still exists?
    public boolean isDead();
}
