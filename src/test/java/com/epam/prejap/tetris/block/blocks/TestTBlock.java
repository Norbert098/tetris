package com.epam.prejap.tetris.block.blocks;

public class TestTBlock extends TestBlock {
    private static final int ROWS = 2;
    private static final int COLS = 3;

    private static final Object[][] blockCoordinates = {
            {0, 0, 1},
            {0, 1, 1},
            {0, 2, 1},
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 1},
    };

    public TestTBlock() {
        super(blockCoordinates, ROWS, COLS);
    }
}
