package testris.playfield;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
    HashMap<String, Float> borderColor;
    ArrayList<ArrayList> boardGrid;
    int tileSize;
    

    public Board (int dWidth, int dHeight, HashMap<String, Float> borderColor) {
        displayWidth = dWidth;
        displayHeight = dHeight;
        this.borderColor = borderColor;
        boardWidth = displayWidth / 3;
        boardHeight = displayHeight;
        createBorder();
        setTileSize();
        createGrid();
//        Testing crap
//        testGrid();
    }

    private void testGrid() {
//        Draw a single blue tile
        HashMap<String, Float> blue = new HashMap<>();
        blue.put("red", 0.0f);
        blue.put("green", 0.0f);
        blue.put("blue", 1.0f);
        HashMap<String, Float> green = new HashMap<>();
        green.put("red", 0.0f);
        green.put("green", 1.0f);
        green.put("blue", 0.0f);
        HashMap<String, Float> red = new HashMap<>();
        red.put("red", 1.0f);
        red.put("green", 0.0f);
        red.put("blue", 0.0f);
        HashMap<String, Float> cyan = new HashMap<>();
        cyan.put("red", 0.0f);
        cyan.put("green", 1.0f);
        cyan.put("blue", 1.0f);
//        ArrayList<BoardTile> boardRow = boardGrid.get(1);
//        BoardTile testTile = boardRow.get(2);
//        testTile.setColor(blue);
//        testTile.setFilled();
//        Draw a row of blue tiles
        ArrayList<BoardTile> bottomTiles = boardGrid.get(19);
        for (BoardTile tile: bottomTiles) {
            tile.setColor(cyan);
            tile.setFilled();
        }
    }

//    private void testMovingTiles

    public Point getPixelLocation(Point gridLocation) {
//        Based on the grid location of the tile return the pixel location
        return new Point(
                (gridLocation.getX() * tileSize) + playFieldPoints.get(0).getX(),
                (gridLocation.getY() * tileSize) + playFieldPoints.get(0).getY()
        );
    }

    private void setTileSize() {
        int boardHeight = playFieldPoints.get(3).getY() - playFieldPoints.get(0).getY();
        int yGridSize = boardHeight / 20;
        int boardWidth = playFieldPoints.get(1).getX() - playFieldPoints.get(0).getX();
        int xGridSize = boardWidth / 10;
        if (yGridSize != xGridSize) {
            System.out.println("x and y tile size disagree");
            System.exit(1);
        }
        tileSize = xGridSize;
    }

    public int getTileSize() {
        return tileSize;
    }

    private void createGrid() {
        boardGrid = new ArrayList<>();
        for (int i=0; i <= 19; i++) {
            ArrayList<BoardTile> newRow = new ArrayList<>();
            for (int j=0; j<=9; j++) {
                Point gridPoint = new Point(j, i);
                int xPixelLoc = (j * tileSize) + playFieldPoints.get(0).getX();
                int yPixelLoc = (i * tileSize) + playFieldPoints.get(0).getY();
                Point pixelLocation =  new Point(xPixelLoc, yPixelLoc);
                BoardTile newTile = new BoardTile(gridPoint, pixelLocation, tileSize);
                newRow.add(newTile);
            }
            boardGrid.add(newRow);
        }
    }

    public ArrayList<ArrayList> getBoardState() {
        return boardGrid;
    }

    public void setBoardState(ArrayList<ArrayList> newBoardState) {
        boardGrid = newBoardState;
    }


    private void createBorder() {
//        TODO Create a class that handles each part of the border
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
//        TODO move this into it's own method
//        Add the play field points
//        It appears that you can't re-use Points that you already added to ArrayLists
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
        return playFieldPoints;
    }

    public void update() {
        ArrayList<Integer> clearedRowIndexes = new ArrayList<>();
//        Should be called on each cycle to see if lines need to be cleared and then update
//        blocks
//        Also need to check for a game over here too
        Boolean clearRow = false;
        for (int i=0; i < boardGrid.size(); i++) {
            clearRow = checkForClear(boardGrid.get(i));
            if (clearRow) {
                clearedRowIndexes.add(i);
            }
        }
        if (clearRow) {
            clearRows(clearedRowIndexes);
        }
    }

    private Boolean checkForClear(ArrayList<BoardTile> boardRow) {
        Boolean rowFull = false;
        for (BoardTile tile : boardRow) {
            if (tile.isEmpty()) {
                rowFull= false;
                break;
            } else {
                rowFull = true;
            }
        }
        return rowFull;
    }

    private void clearRows(ArrayList<Integer> rows) {
        System.out.println("Clearing rows");
        Collections.reverse(rows);
        ArrayList<ArrayList> newBoard = new ArrayList<>();
        List<ArrayList> rowsToUpdate = new ArrayList<>();
        List<ArrayList> remainingRows = new ArrayList<>();
        for (Integer row : rows) {
//            If this even worked it would only work for one line :(
            System.out.println("Row: " + row);
            rowsToUpdate = boardGrid.subList(0, row);
            remainingRows = boardGrid.subList(row, 20);
            Collections.reverse(rowsToUpdate);
            rowsToUpdate.remove(0);
            Collections.reverse(rowsToUpdate);
            ArrayList<BoardTile> newRow = new ArrayList<>();
            for (int j=0; j<=9; j++) {
                Point gridPoint = new Point(j, row);
                int xPixelLoc = (j * tileSize) + playFieldPoints.get(0).getX();
                int yPixelLoc = (0 * tileSize) + playFieldPoints.get(0).getY();
                Point pixelLocation = new Point(xPixelLoc, yPixelLoc);
                BoardTile newTile = new BoardTile(gridPoint, pixelLocation, tileSize);
                newRow.add(newTile);
                rowsToUpdate.add(newRow);
            }
        }
//        rowsToUpdate.addAll(remainingRows);
//        newBoard.addAll(rowsToUpdate);
//        newBoard.addAll(remainingRows);
//        newBoard.addAll(rowsToUpdate);
        System.out.println(rowsToUpdate);
        for (ArrayList<BoardTile> newRow : rowsToUpdate) {
            newBoard.add(newRow);
        }
        System.out.println(newBoard);
        System.out.println(remainingRows);
        for (ArrayList<BoardTile> newRow : remainingRows) {
            newBoard.add(newRow);
        }
        boardGrid = newBoard;
    }

    public void addBlock(BoardTile newTile) {
        ArrayList<BoardTile> rowTiles = boardGrid.get(newTile.getGridLocation().getY());
        BoardTile updatedTile = rowTiles.get(newTile.getGridLocation().getX());
        updatedTile.setColor(newTile.getColor());
        updatedTile.setFilled();
//        register blocks to the board once they have hit the bottom of the board
//        where the bottom is either the actual bottom of the board or the highest added block
    }

    public ArrayList<ArrayList> getTiles() {
        return boardGrid;
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
//        Draw tiles
        for (ArrayList<BoardTile> rowTiles: boardGrid) {
            for (BoardTile tile: rowTiles) {
                    tile.draw();
            }
        }
//        Draw borders
        drawBorder();
    }
}
