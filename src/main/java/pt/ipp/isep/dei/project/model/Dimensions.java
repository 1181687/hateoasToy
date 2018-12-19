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
}
