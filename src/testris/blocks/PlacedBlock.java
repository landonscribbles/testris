package testris.blocks;

public class PlacedBlock {
    private int[] drawPoints = new int[4];
    private int[] gridLocation = new int[2];
    private float[] blockColor = new float[4];
    private boolean blockIsDead;

    public PlacedBlock(int[] drawPoints, float[] color, int[] gridLocation) {
        this.drawPoints = drawPoints;
        this.gridLocation = gridLocation;
        this.blockColor = color;
        this.blockIsDead = false;
    }

    public int[] getDrawPoints() {
        return this.drawPoints;
    }

    public void setDrawPoints(int[] drawPoints) {
        this.drawPoints = drawPoints;
    }

    public float[] getColor() {
        return this.blockColor;
    }

    public int[] getGridLocation() {
        return this.gridLocation;
    }

    public void setGridLocation(int[] gridLocation) {
        this.gridLocation = gridLocation;
    }
    // This should be moved into the placed blocks

    // When given a row number that is cleared if the block exists on that row
    // remove the block entirely
    public void clearRow(int rowNumber) {
        if (rowNumber == this.gridLocation[1]) {
            this.blockIsDead = true;
            // Delete this instance
            // Remove it from the playfield?
        }
    }

    // After a clear the blocks will need to update their location downward
    // the number of rows that are cleared
    public void moveBlockDown(int rowNumberDown) {
        this.gridLocation[1] = this.gridLocation[1] + rowNumberDown;
    }

    public boolean isDead() {
        return blockIsDead;
    }

}
