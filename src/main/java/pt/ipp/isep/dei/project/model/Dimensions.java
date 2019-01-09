package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class Dimensions {
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
    public Dimensions(double height, double length, double width) {
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
    public double getmHeight() {
        return mHeight;
    }

    /**
     * method that get the length of a dimension
     * @return a length
     */
    public double getmLength() {
        return mLength;
    }

    /**
     * method that get the length of a dimension
     * @return a width
     */
    public double getmWidth() {
        return mWidth;
    }

    /**
     * set the heigth of the dimension
     * @param mHeight
     */
    public void setmHeight(double mHeight) {
        this.mHeight = mHeight;
    }

    /**
     * set the length of the dimension
     * @param mLength
     */
    public void setmLength(double mLength) {
        this.mLength = mLength;
    }

    /**
     * set the width of the dimension
     * @param mWidth
     */
    public void setmWidth(double mWidth) {
        this.mWidth = mWidth;
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
     * Equals method to determine if two Dimensions are equal.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dimensions)) {
            return false;
        }
        Dimensions dim = (Dimensions) obj;
        final double delta = 0.0001;
        return Math.abs((this.mHeight - dim.mHeight)) <= delta && Math.abs((this.mLength - dim.mLength)) <= delta
                && Math.abs((this.mWidth - dim.mWidth)) <= delta;
    }
}
