package testris.blocks;

public class PlayerBlock implements Block {
    private int[] drawPoints = new int[4];
    private int[] gridLocation = new int[2];
    private float[] blockColor = new float[4];
    private boolean blockIsDead;

    public PlayerBlock(int[] drawPoints, float[] color, int[] gridLocation) {
        this.drawPoints = drawPoints;
        this.gridLocation = gridLocation;
        this.blockColor = color;
        this.blockIsDead = false;
    }

    public int[] getDrawPoints() { return this.drawPoints; }

    public void setDrawPoints(int[] drawPoints) { this.drawPoints = drawPoints; }

    public float[] getColor() { return this.blockColor; }

    public int[] getGridLocation() { return this.gridLocation; }

    public void setGridLocation(int[] gridLocation) { this.gridLocation = gridLocation; }

    public boolean isDead() { return blockIsDead; }

    // Check collision and then set dead if so

    // Take input from the player and do something, should this be on the playfield?
}
