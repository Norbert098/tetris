package com.epam.prejap.tetris.block;

import java.util.Deque;
import java.util.LinkedList;

public class BlockRotator {

    private static final Point NO_OFFSET = new Point(0, 0);

    private Block block;
    private Point blockOffset;

    private final LastBlockRotation lastRotation;
    private boolean rotationBackLocked;
    private Point rotationPoint;

    public BlockRotator(Block block) {
        this.block = block;
        rotationPoint = blockRotationPoint();
        blockOffset = NO_OFFSET;

        lastRotation = new LastBlockRotation();
        rotationBackLocked = true;
    }

    public Block getRotatedBlock() {
        return block;
    }

    public Point getBlockOffset() {
        return blockOffset;
    }

    public void rotate() {
        saveLastRotation();

        int rotatedRows = block.cols();
        int rotatedColumns = block.rows();
        Point rotatedImageSize = new Point(rotatedRows, rotatedColumns);

        byte[][] rotatedImage = rotateBlockImage90DegreesClockwise(rotatedImageSize);
        blockOffset = setNewRotationPoint(rotatedImageSize);

        block = block.copyWithImage(rotatedImage);
    }

    private void saveLastRotation() {
        rotationBackLocked = false;
        lastRotation.block = block;
        lastRotation.rotationPoint = rotationPoint;
        lastRotation.blockOffset = blockOffset;
    }

    public void rotateBack() {
        if (rotationBackLocked) {
            throw new CannotRotateBackException("Before each rotateBack at least one rotate call must be made!");
        }
        block = lastRotation.block;
        blockOffset = lastRotation.blockOffset;
        rotationPoint = lastRotation.rotationPoint;
        rotationBackLocked = true;
    }

    private class LastBlockRotation {
        private Block block;
        private Point blockOffset;
        private Point rotationPoint;
    }

    Point blockRotationPoint() {
        int middleRow = block.rows()/2;
        int middleColumn = block.cols()/2;
        return new Point(middleRow, middleColumn);
    }

    /**
     * Calculates and sets {@link BlockRotator#rotationPoint} to rotation point on a rotated block.
     * @param   rotatedImageSize size of a rotated image to which rotation point will be rotated
     * @return  an offset(distance) between rotation point before and after rotation
     */
    private Point setNewRotationPoint(Point rotatedImageSize) {
        Point newRotationPoint = rotatePointOnRotatedImage(rotationPoint, rotatedImageSize);
        var blockOffset = calculateOffset(rotationPoint, newRotationPoint);
        rotationPoint = newRotationPoint;

        boolean blockImageIsSquare = block.cols() == block.rows();
        if (blockImageIsSquare) {
            return NO_OFFSET;
        }

        return blockOffset;
    }

    private Point calculateOffset(Point from, Point to) {
        int row = from.row - to.row;
        int column = from.column - to.column;
        return new Point(row, column);
    }

    private byte[][] rotateBlockImage90DegreesClockwise(Point rotatedImageSize) {
        byte[][] rotatedImage = emptyRotatedImage(rotatedImageSize);

        for (int row = 0; row < block.rows(); row++) {
            for (int column = 0; column < block.cols(); column++) {
                Point toRotate = new Point(row, column);
                rotatePointOnImage(rotatedImage, rotatedImageSize, toRotate);
            }
        }
        return rotatedImage;
    }

    private byte[][] emptyRotatedImage(Point rotatedImageSize) {
        return new byte[rotatedImageSize.row][rotatedImageSize.column];
    }

    private void rotatePointOnImage(byte[][] rotatedImage, Point rotatedImageSize, Point toRotate) {
        Point rotated = rotatePointOnRotatedImage(toRotate, rotatedImageSize);
        rotatedImage[rotated.row][rotated.column] = block.dotAt(toRotate.row, toRotate.column);
    }

    Point rotatePointOnRotatedImage(Point toRotate, Point rotatedImageSize) {
        int rotatedRow = toRotate.column;
        int rotatedColumn = rotatedImageSize.column - 1 - toRotate.row;
        return new Point(rotatedRow, rotatedColumn);
    }

}
