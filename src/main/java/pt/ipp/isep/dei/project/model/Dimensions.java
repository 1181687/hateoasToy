package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class Dimensions {
    private double mHeight;
    private double mLength;
    private double mWidth;

    ///////////////////////////////////////////////////////////////////////////////
    public Dimensions(double height, double length, double width) {
        validateHeight(height);
        validateLength(length);
        validateWidth(width);
        this.mHeight = height;
        this.mLength = length;
        this.mWidth = width;
    }

    private void validateHeight(double height) {
        if (Double.isNaN(height) || height <= 0) {
            throw new RuntimeException("Please enter a valid height. Height should be greater than zero");
        }
    }

    private void validateLength(double length) {
        if (Double.isNaN(length) || length <= 0) {
            throw new RuntimeException("Please enter a valid length. Length should be greater than zero");
        }
    }

    private void validateWidth(double width) {
        if (Double.isNaN(width) || width <= 0) {
            throw new RuntimeException("Please enter a valid width. Width should be greater than zero");
        }
    }

    ///////////////////////////////////////////////////////////////////
    public double getmHeight() {
        return mHeight;
    }

    public double getmLength() {
        return mLength;
    }

    public double getmWidth() {
        return mWidth;
    }

    public void setmHeight(double mHeight) {
        this.mHeight = mHeight;
    }

    public void setmLength(double mLength) {
        this.mLength = mLength;
    }

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
