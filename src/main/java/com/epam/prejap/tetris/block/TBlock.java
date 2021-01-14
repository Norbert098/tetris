package com.epam.prejap.tetris.block;

import java.util.List;

/**
 * <b>class Block which represent shape of "T" letter</b>
 *
 * @author Norbert Borek
 * @implNote To create image representation of letter "T"
 * two-dimensional array IMAGE is simply modified.
 * @see Block
 */
final class TBlock extends Block {

    private static final List<byte[][]> IMAGES = List.of(
            new byte[][]{
                    {1, 1, 1},
                    {0, 1, 0},
            },
            new byte[][]{
                    {0, 1},
                    {1, 1},
                    {0, 1},
            },
            new byte[][]{
                    {1, 1, 1},
                    {0, 1, 0},
            },
            new byte[][]{
                    {0, 1},
                    {1, 1},
                    {0, 1},
            }
    );

    public TBlock() {
        super(IMAGES, Color.WHITE);
    }

    private TBlock(List<byte[][]> image, Color color, int imageIndex) {
        super(image, color, imageIndex);
    }

    @Override
    Block copyWithImageIndex(int imageIndex) {
        return new TBlock(this.images, this.color, imageIndex);
    }
}
