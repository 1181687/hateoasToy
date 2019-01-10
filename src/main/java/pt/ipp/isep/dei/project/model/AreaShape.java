package pt.ipp.isep.dei.project.model;

public class AreaShape {
    private double mLength;
    private double mWidth;
    private Location mLocationAreaShape;

    /**
     * constructor of AreaShape that receives a width, a length and a locationAreaShape.
     * @param width
     * @param length
     * @param locationAreaShape
     */
    public AreaShape(double width, double length, Location locationAreaShape) {
        mLength = length;
        mWidth = width;
        mLocationAreaShape = locationAreaShape;
    }

    /**
     * method that check if a location is inserted in an area
     * @param location
     * @return a location with their geographical coordinates
     */
    public boolean checkIfLocationIsInsertedInAnArea(Location location){
        double upperLeftCornerLatitude = mLocationAreaShape.getmLatitude() + (mWidth /2);
        double upperLeftCornerLongitude = mLocationAreaShape.getmLongitude() - (mLength /2);

        double bottomRightCornerLatitude = mLocationAreaShape.getmLatitude() - (mWidth /2);
        double bottomRightCornerLongitude = mLocationAreaShape.getmLongitude() + (mLength /2);

        return (location.getmLatitude() >= bottomRightCornerLatitude
                && location.getmLatitude() <= upperLeftCornerLatitude
                && location.getmLongitude() <= bottomRightCornerLongitude
                && location.getmLongitude() >= upperLeftCornerLongitude);
    }

    /**
     * method that creates the hashcode to address
     */
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two AreaShape are equal.
     * They are equals if all atributtes (mLength, mWidth and mLocationAreaShape) are equal.
     * @param obj
     * @return boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AreaShape)) {
            return false;
        }

        AreaShape local = (AreaShape) obj;
        Double comparablemLength = mLength;
        Double comparablemWidth = mWidth;
        Location comparablemLocationAreaShape = mLocationAreaShape;

        Double comparableAreaShapemLength = local.mLength;
        Double comparableAreaShapemWidth = local.mWidth;
        Location comparableAreaShapemLocationAreaShape = local.mLocationAreaShape;

        return comparableAreaShapemLength.equals(comparablemLength)
                && comparableAreaShapemWidth.equals(comparablemWidth)
                && comparableAreaShapemLocationAreaShape.equals(comparablemLocationAreaShape);
    }

}