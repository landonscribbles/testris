package testris.blocks;

import org.lwjgl.util.Point;
import testris.playfield.Board;
import testris.playfield.BoardTile;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerBlock {
    Board playBoard;
    private HashMap<String, Float> blockColor;
    private boolean blockIsControlled;
    private boolean isGameOver;
    private String blockType;
    private ArrayList<BoardTile> pieceSections;
    private Integer normalDownRate;
    private Boolean isDownPressed;
    private Integer playerDownRate;
    private String currentBlockState; // Choice should be "down", "left", "up", "right" and rotate in that order
//    Testing
    HashMap<String, Float> cyan = new HashMap<>();


    public PlayerBlock(String blockType, Board playBoard) {
//        Testing
        cyan.put("red", 0.0f);
        cyan.put("green", 1.0f);
        cyan.put("blue", 1.0f);
        normalDownRate = 60;
        playerDownRate = 4;
        blockIsControlled = true;
        isGameOver = false;
        isDownPressed = false;
        currentBlockState = "down";
        this.playBoard = playBoard;
        this.blockType = blockType;
        System.out.println("Block type is: " + this.blockType);
        generateTiles();
    }
//    Player blocks should be passed in the board or board state of where the top collidable
//    edge is to then check each of it's sections to see if it is going to collide on each
//    downward movement if it does it should be added to the board and then be marked
//    as no longer controllable

    public Boolean isDead(){
        return !blockIsControlled;
    }

    public Boolean isGameOver() {
        return isGameOver;
    }

    public void moveLeft() {
        ArrayList<Point> originalPoints = new ArrayList<>();
        for (BoardTile tile: pieceSections) {
            Point oldGridPoint = tile.getGridLocation();
            int newX = oldGridPoint.getX() - 1;
            Point newGrid = new Point(newX, oldGridPoint.getY());
            originalPoints.add(oldGridPoint);
            tile.setGridLocation(newGrid);
            Point newPixelLocatoin = playBoard.getPixelLocation(tile.getGridLocation());
            tile.setPixelLocation(newPixelLocatoin);
            if (tile.getGridLocation().getX() < 0) {
//                unwind changes here
                for (int i=0; i < originalPoints.size(); i++) {
                    BoardTile fixingTile = pieceSections.get(i);
                    Point fixedGridLoc = new Point(fixingTile.getGridLocation().getX() + 1, fixingTile.getGridLocation().getY());
                    fixingTile.setGridLocation(fixedGridLoc);
                    Point fixedPixelLoc = playBoard.getPixelLocation(fixedGridLoc);
                    fixingTile.setPixelLocation(fixedPixelLoc);
                }
                break;
            }
        }
    }

    public void moveRight() {
        ArrayList<Point> originalPoints = new ArrayList<>();
        for (BoardTile tile: pieceSections) {
            Point oldGridPoint = tile.getGridLocation();
            int newX = oldGridPoint.getX() + 1;
            Point newGrid = new Point(newX, oldGridPoint.getY());
            originalPoints.add(oldGridPoint);
            tile.setGridLocation(newGrid);
            Point newPixelLocatoin = playBoard.getPixelLocation(tile.getGridLocation());
            tile.setPixelLocation(newPixelLocatoin);
            if (tile.getGridLocation().getX() > 9) {
//                unwind changes here
                for (int i=0; i < originalPoints.size(); i++) {
                    BoardTile fixingTile = pieceSections.get(i);
                    Point fixedGridLoc = new Point(fixingTile.getGridLocation().getX() - 1, fixingTile.getGridLocation().getY());
                    fixingTile.setGridLocation(fixedGridLoc);
                    Point fixedPixelLoc = playBoard.getPixelLocation(fixedGridLoc);
                    fixingTile.setPixelLocation(fixedPixelLoc);
                }
                break;
            }
        }
    }

    public void setDownPressed() {
        isDownPressed =  true;
    }

    public void releasedDownPressed() {
        isDownPressed = false;
    }

    public void update(Integer frameNumber) {
//        TODO collision checking is going to need to happen here
        int downRate;
        if (isDownPressed) {
            downRate = playerDownRate;
        } else {
            downRate = normalDownRate;
        }
        if (frameNumber % downRate == 0 && blockIsControlled) {
            for (BoardTile tile: pieceSections) {
                Point newGridLocation = new Point(tile.getGridLocation().getX(), tile.getGridLocation().getY() + 1);
                tile.setGridLocation(newGridLocation);
                Point newPixelLocation = playBoard.getPixelLocation(tile.getGridLocation());
                tile.setPixelLocation(newPixelLocation);
            }
        }
        checkCollision();
    }

    private void checkCollision() {
        Boolean collided = false;
        ArrayList<ArrayList> boardGrid = playBoard.getTiles();
        for (ArrayList<BoardTile> tileRow: boardGrid) {
            for (BoardTile boardTile: tileRow) {
                if (boardTile.isEmpty()) {
                    continue;
                } else {
                    for (BoardTile pieceTile: pieceSections) {
                        Point boardGridPoint = boardTile.getGridLocation();
                        Point pieceGridPoint = pieceTile.getGridLocation();
                        if ((boardGridPoint.getY() == pieceGridPoint.getY()) && boardGridPoint.getX() == pieceGridPoint.getX()) {
                            collided = true;
                        }
                    }
                }
            }
        }
        if (collided) {
            for (BoardTile tile : pieceSections) {
                Point newGridLocation = new Point(tile.getGridLocation().getX(), tile.getGridLocation().getY() - 1);
                if (newGridLocation.getY() < 0) {
                    isGameOver = true;
                }
                tile.setGridLocation(newGridLocation);
                Point newPixelLocation = playBoard.getPixelLocation(tile.getGridLocation());
                tile.setPixelLocation(newPixelLocation);
            }
            blockIsControlled = false;
            if (!isGameOver) {
                addBlockToBoard();
            }
        }
    }

    private void addBlockToBoard() {
        for (BoardTile tile : pieceSections) {
            playBoard.addBlock(tile);
        }
    }


    public void generateTiles() {
        pieceSections = new ArrayList<>();
        if (blockType == "L") {
            int startingTile = 3;
            for (int i=0; i<4; i++){
                Point gridLocation = new Point(startingTile+i, 0);
                Point pixelLoc = playBoard.getPixelLocation(gridLocation);
//                System.out.println("Pixel Loc: " + pixelLoc);
                BoardTile newTile = new BoardTile(gridLocation, pixelLoc, playBoard.getTileSize());
                newTile.setColor(cyan);
                newTile.setToDraw();
                pieceSections.add(newTile);
            }
        }
    }

    public String getBlockType() {
        return  blockType;
    }

    public void draw(){
        for (BoardTile tile: pieceSections) {
            tile.draw();
        }

    }

//    private Boolean canRotate() {
////        Check the collision against the board to see if a block can be turned
//        return true;
//    }

    private Boolean willCollideWithBoard(ArrayList<Point> newPoints) {
        Boolean willCollide = false;
        ArrayList<ArrayList> playTiles = playBoard.getTiles();
        for (ArrayList<BoardTile> rowTiles: playTiles) {
            for (BoardTile playTile: rowTiles) {
                if (playTile.isEmpty()) {
                    continue;
                }
                for (Point newPoint : newPoints) {
                    if ((playTile.getGridLocation().getX() == newPoint.getX()) && (playTile.getGridLocation().getY() == newPoint.getY())) {
                        willCollide = true;
                    }
                }
            }
        }
        return willCollide;
    }

    public void rotate() {

        if (blockType == "L") {
            System.out.println("Attempting to rotate L...");
            rotateLBlock();
        }
    }

    private void rotateLBlock() {
        ArrayList<Point> rotationLocation = new ArrayList<>();
        if (currentBlockState == "down") {
            int moveY = 0; // If we're at the bottom of the board move up
            int currentGridYPoint = pieceSections.get(1).getGridLocation().getY();
            if (currentGridYPoint >= 18) {
                moveY = -1;
            }
            if (currentGridYPoint <= 1) {
                return;
            } else {
                Point currentGridPoint = pieceSections.get(1).getGridLocation();
                Point startPoint = new Point(currentGridPoint.getX(), currentGridPoint.getY() - 2 + moveY);
                for (int i=0; i < pieceSections.size(); i++) {
                    Point newPoint = new Point(startPoint.getX(), startPoint.getY() + i);
                    rotationLocation.add(newPoint);
                }
                currentBlockState = "left";
            }
        } else if (currentBlockState == "left") {
            int moveX = 0; // If we're too close to the left/right side of the board
            int currentGridXPoint = pieceSections.get(1).getGridLocation().getX();
            if (currentGridXPoint <= 1) {
                moveX = 1;
            }
            if (currentGridXPoint >= 8) {
                moveX = -2;
            }
            Point currentGridPoint = pieceSections.get(1).getGridLocation();
            Point startPoint = new Point(currentGridPoint.getX() - 1 + moveX, currentGridPoint.getY());
            for (int i=0; i < pieceSections.size(); i++) {
                Point newPoint = new Point(startPoint.getX() + i, startPoint.getY());
                rotationLocation.add(newPoint);
            }
            currentBlockState = "up";
        } else if (currentBlockState == "up") {
            int moveY = 0;
            int currentGridYPoint = pieceSections.get(2).getGridLocation().getY();
            if (currentGridYPoint >= 17) {
                moveY = -2;
            }
            if (currentGridYPoint <= 0) {
                return;
            } else {
                Point currentGridPoint = pieceSections.get(2).getGridLocation();
                Point startPoint = new Point(currentGridPoint.getX(), currentGridPoint.getY() + moveY);
                for (int i=0; i < pieceSections.size(); i++) {
                    Point newPoint = new Point(startPoint.getX(), startPoint.getY() + i);
                    rotationLocation.add(newPoint);
                }
                currentBlockState = "right";
            }
        } else if (currentBlockState == "right") {
            int moveX = 0;
            int currentGridXPoint = pieceSections.get(2).getGridLocation().getX();
            if (currentGridXPoint <= 2) {
                moveX = 2;
            }
            if (currentGridXPoint >=9) {
                moveX = -1;
            }
            Point currentGridPoint = pieceSections.get(2).getGridLocation();
            Point startPoint = new Point(currentGridPoint.getX() - 2 + moveX, currentGridPoint.getY() - 1);
            for (int i=0; i < pieceSections.size(); i++) {
                Point newPoint = new Point(startPoint.getX() + i, startPoint.getY());
                rotationLocation.add(newPoint);
            }
            currentBlockState = "down";
        }
        if(!willCollideWithBoard(rotationLocation)) {
            System.out.println("No collision, turning!");
            ArrayList<BoardTile> tempPieceSections =  new ArrayList<>();
            for (int i=0; i < pieceSections.size(); i++) {
                Point newPoint = rotationLocation.get(i);
                Point fixedPixel = playBoard.getPixelLocation(newPoint);
                BoardTile newBoardTile = new BoardTile(newPoint, fixedPixel, playBoard.getTileSize());
                newBoardTile.setColor(cyan);
                newBoardTile.setToDraw();
                tempPieceSections.add(newBoardTile);
                }
            pieceSections = tempPieceSections;

        } else {
            System.out.println("Failed to turn due to collision");
        }
    }

    public HashMap<String, Float> getColor() {
        return blockColor;
    }


    // Check collision and then set dead if so

//    Take input from the player and do something, if this is a rotate there is an internal timer to allow blocks to
//    rotate indefinitely as long as the player continues to press the rotate button before the internal rotate timer
//    expires
}
