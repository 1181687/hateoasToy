package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class Dimension {
    private double mHeight;
    private double mLength;
    private double mWidth;

    /**
     * constructor that receives height, length, width and
     * throw an exception if any of the parameters is invalid.
     * Invalid parameters if they are doubleNan or equal or less than zero
     *
     * @param height given height
     * @param length given length
     * @param width  given width
     */
    public Dimension(double height, double length, double width) {
        validateHeight(height);
        validateLength(length);
        validateWidth(width);
        this.mHeight = height;
        this.mLength = length;
        this.mWidth = width;
    }

    /**
     * method that receives a height and validates it. It can not be doubleNan or equal or less than zero
     * throw an exception if the height is invalid
     *
     * @param height given height
     */
    private static void validateHeight(double height) {
        if (Double.isNaN(height) || height <= 0) {
            throw new RuntimeException("Please enter a valid height. Height should be greater than zero");
        }
    }

    /**
     * method that receives a length and validates it. It can not be doubleNan or equal or less than zero
     * throw an exception if the length is invalid
     *
     * @param length given length
     */
    private static void validateLength(double length) {
        if (Double.isNaN(length) || length <= 0) {
            throw new RuntimeException("Please enter a valid length. Length should be greater than zero");
        }
    }

    /**
     * method that receives a width and validates it. It can not be doubleNan or equal or less than zero
     * throw an exception if the width is invalid
     *
     * @param width given width
     */
    private static void validateWidth(double width) {
        if (Double.isNaN(width) || width <= 0) {
            throw new RuntimeException("Please enter a valid width. Width should be greater than zero");
        }
    }

    /**
     * method that get the height of a dimension
     * @return a height
     */
    public double getHeight() {
        return mHeight;
    }

    /**
     * set the heigth of the dimension
     *
     * @param height
     */
    public void setHeight(double height) {
        this.mHeight = height;
    }

    /**
     * method that get the length of a dimension
     * @return a length
     */
    public double getLength() {
        return mLength;
    }

    /**
     * set the length of the dimension
     *
     * @param length
     */
    public void setLength(double length) {
        this.mLength = length;
    }

    /**
     * method that get the length of a dimension
     * @return a width
     */
    public double getWidth() {
        return mWidth;
    }

    /**
     * set the width of the dimension
     * @param width
     */
    public void setWidth(double width) {
        this.mWidth = width;
    }

    /**
     * method that creates the same hashcode to dimensions with the same attributes: height, length, and width.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(mHeight, mLength, mWidth);
    }


    /**
     * Equals method to determine if two Dimension are equal.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dimension)) {
            return false;
        }
        Dimension dim = (Dimension) obj;
        Double comparableHeight = mHeight;
        Double comparableLength = mLength;
        Double comparableWidth = mWidth;
        Double comparableDimensionsHeight = dim.mHeight;
        Double comparableDimensionsLength = dim.mLength;
        Double comparableDimensionsWidth = dim.mWidth;
        return comparableDimensionsHeight.equals(comparableHeight)
                && comparableDimensionsLength.equals(comparableLength)
                && comparableDimensionsWidth.equals(comparableWidth);
    }
}
