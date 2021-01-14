package com.epam.prejap.tetris.block;

/**
 * <b>class Block which represent shape of "T" letter</b>
 *
 * @author Norbert Borek
 * @implNote To create image representation of letter "T"
 * I've simply modified second row of two-dimensional array IMAGE.
 * @see Block
 */
final class TBlock extends Block {

    private static final byte[][] IMAGE = {
            {1, 1, 1},
            {0, 1, 0},
    };

    public TBlock() {
        super(IMAGE, Color.WHITE);
    }
}
