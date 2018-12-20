package pt.ipp.isep.dei.project.model;

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

    @Override
    public int hashCode() {
        return 1;
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
        return this.mHeight==dim.mHeight && this.mLength==dim.mLength && this.mWidth==dim.mWidth;
    }
}
