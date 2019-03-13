package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

public class AreaShape {
    private double length;
    private double width;
    private Location locationAreaShape;

    /**
     * constructor of AreaShape that receives a width, a length and a locationAreaShape.
     * @param width
     * @param length
     * @param locationAreaShape
     */
    public AreaShape(double width, double length, Location locationAreaShape) {
        this.length = length;
        this.width = width;
        this.locationAreaShape = locationAreaShape;
    }

    /**
     * method that check if a location is inserted in an area
     * @param location
     * @return a location with their geographical coordinates
     */
    public boolean checkIfLocationIsInsertedInAnArea(Location location){
        double upperLeftCornerLatitude = locationAreaShape.getLatitude() + (width / 2);
        double upperLeftCornerLongitude = locationAreaShape.getLongitude() - (length / 2);

        double bottomRightCornerLatitude = locationAreaShape.getLatitude() - (width / 2);
        double bottomRightCornerLongitude = locationAreaShape.getLongitude() + (length / 2);

        return (location.getLatitude() >= bottomRightCornerLatitude
                && location.getLatitude() <= upperLeftCornerLatitude
                && location.getLongitude() <= bottomRightCornerLongitude
                && location.getLongitude() >= upperLeftCornerLongitude);
    }

    /**
     * method that creates the hashcode to address
     */
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two AreaShape are equal.
     * They are equals if all atributtes (length, width and locationAreaShape) are equal.
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
        Double comparablemLength = length;
        Double comparablemWidth = width;
        Location comparablemLocationAreaShape = locationAreaShape;

        Double comparableAreaShapemLength = local.length;
        Double comparableAreaShapemWidth = local.width;
        Location comparableAreaShapemLocationAreaShape = local.locationAreaShape;

        return comparableAreaShapemLength.equals(comparablemLength)
                && comparableAreaShapemWidth.equals(comparablemWidth)
                && comparableAreaShapemLocationAreaShape.equals(comparablemLocationAreaShape);
    }

}