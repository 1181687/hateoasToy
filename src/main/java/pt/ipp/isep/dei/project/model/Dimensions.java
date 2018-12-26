package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class Dimensions {
    private double mHeight;
    private double mLength;
    private double mWidth;

    public Dimensions(double mHeight, double mLength, double mWidth) {
        this.mHeight = mHeight;
        this.mLength = mLength;
        this.mWidth = mWidth;
    }

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
        return Math.abs((this.mHeight-dim.mHeight))< delta && Math.abs((this.mLength-dim.mLength))< delta
                && Math.abs((this.mWidth-dim.mWidth))<delta;
    }
    ////////////////////////////////////////////////////////
}
