package com.epam.prejap.tetris.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Test(groups = {"Block", "TBlock"})
public class TBlockTest {

    @Test
    public void testTBlockShape() {
        //given
        SoftAssert softAssert = new SoftAssert();
        TBlock TBlock = new TBlock();
        byte[][] expectedImage = new byte[2][3];

        //when
        int actualRows = TBlock.rows;
        int actualCols = TBlock.cols;

        //then
        softAssert.assertTrue(actualRows == expectedImage.length);
        softAssert.assertTrue(actualCols == expectedImage[0].length);
        softAssert.assertAll("Shall create T block with correct dimensions, but did not");
        TBlock.IllegalArgumentException(actualRows == actualCols, "The image is not in shape of letter T");
    }

     @Test(dataProvider = "dotsForTBlock")
    public void shallCreateTBlockWithTShapedDots(int row, int col) {
        //given
        Block TBlock = new TBlock();
        int dotMark = 1;

        //when
        byte actualDot = TBlock.dotAt(row, col);

        //then
        assertEquals(actualDot, dotMark, "Shall create T block with correct shaped dots, but did not");
    }

    @DataProvider()
    public static Object[] dotsForTBlock() {
        return new Object[][]{
                {0, 0},
                {1, 1},
                {0, 1},
                {0, 0},
        };
    }

    @DataProvider
    public static Object[] TBlockShape() {
        byte[][] TBlock = {
                {1, 1, 1},
                {0, 1, 0}};
        return new Object[]{TBlock};
    }
}
