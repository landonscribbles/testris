package testris.blocks;

import testris.playfield.Board;

import java.util.ArrayList;
import java.util.Random;

public class BlockGenerator {

    Board playBoard;
    ArrayList<String> blockTypes;
    Random rand;

    public BlockGenerator(Board playBoard) {
        this.playBoard = playBoard;
        rand = new Random();
        blockTypes = new ArrayList<>();
        blockTypes.add("L");
    }

    private String getNewBlockType() {
        return blockTypes.get(rand.nextInt(blockTypes.size()));
    }

    public PlayerBlock generateBlock() {
//        Let's start with just returning an "l" block
        String newBlockType = getNewBlockType();
        PlayerBlock newBlock = new PlayerBlock(newBlockType, playBoard);
        return newBlock;
    }

}
